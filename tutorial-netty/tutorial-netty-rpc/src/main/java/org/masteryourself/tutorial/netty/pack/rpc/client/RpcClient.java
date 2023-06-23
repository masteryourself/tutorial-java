package org.masteryourself.tutorial.netty.pack.rpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.netty.pack.rpc.message.PingMessage;
import org.masteryourself.tutorial.netty.pack.rpc.message.RpcRequestMessage;
import org.masteryourself.tutorial.netty.pack.rpc.protocol.MessageCodecSharable;
import org.masteryourself.tutorial.netty.pack.rpc.protocol.ProtocolFrameDecoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>description : RpcClient
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/18 12:30 AM
 */
@Slf4j
public class RpcClient {

    private static final Channel channel;

    static {
        channel = initChannel();
    }

    private static final AtomicInteger REQUEST_COUNT = new AtomicInteger(0);

    @SuppressWarnings("all")
    public static <T> T getProxyService(Class<T> serviceInterface) {
        return (T) Proxy.newProxyInstance(
                serviceInterface.getClassLoader(),
                new Class[]{serviceInterface},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 1. 构造 rpc 请求
                        int sequenceId = REQUEST_COUNT.incrementAndGet();
                        RpcRequestMessage rpcRequestMessage = new RpcRequestMessage(
                                sequenceId,
                                serviceInterface.getName(),
                                method.getName(),
                                method.getReturnType(),
                                method.getParameterTypes(),
                                args
                        );
                        // 2. 发送 rpc 请求
                        channel.writeAndFlush(rpcRequestMessage);
                        // 3. 准备一个空 Promise 对象，来接收结果, 指定 promise 对象异步接收结果线程
                        DefaultPromise<Object> promise = new DefaultPromise<>(channel.eventLoop());
                        ClientRequestHandler.PROMISES.put(sequenceId, promise);
                        // 4. 阻塞知道有结果
                        promise.await();
                        // 5. 此时已经获取到结果, 解析
                        if (promise.isSuccess()) {
                            // 正常返回
                            return promise.getNow();
                        } else {
                            // 包装异常
                            throw new RuntimeException(promise.cause());
                        }
                    }
                });
    }

    private static Channel initChannel() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        ClientRequestHandler CLIENT_REQUEST_HANDLER = new ClientRequestHandler();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(group);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ProtocolFrameDecoder());
                    ch.pipeline().addLast(MESSAGE_CODEC);
                    // 业务处理
                    ch.pipeline().addLast(CLIENT_REQUEST_HANDLER);
                    // 用来判断是不是[读空闲时间过长]，或[写空闲时间过长]
                    // 3s 内如果没有向服务器写数据，会触发一个 IdleState#WRITER_IDLE 事件
                    ch.pipeline().addLast(new IdleStateHandler(0, 3, 0));
                    // ChannelDuplexHandler 可以同时作为入站和出站处理器
                    ch.pipeline().addLast(new ChannelDuplexHandler() {
                        // 用来触发特殊事件
                        @Override
                        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                            IdleStateEvent event = (IdleStateEvent) evt;
                            // 触发了写空闲事件
                            if (event.state() == IdleState.WRITER_IDLE) {
                                ctx.writeAndFlush(new PingMessage());
                            }
                        }
                    });
                }
            });
            // 这里需要 sync 同步确保连接建立
            Channel channel = bootstrap.connect("localhost", 8080).sync().channel();
            // 这里需要异步监听 channel 关闭事件
            channel.closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    group.shutdownGracefully();
                    if (!future.isSuccess()) {
                        Throwable cause = future.cause();
                        log.error("客户端 channel 关闭异常", cause);
                    }
                }
            });
            return channel;
        } catch (Exception e) {
            log.error("client init error", e);
        }
        return null;
    }

}
