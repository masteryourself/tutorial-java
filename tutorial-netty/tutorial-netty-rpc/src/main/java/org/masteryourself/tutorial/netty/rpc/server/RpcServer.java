package org.masteryourself.tutorial.netty.rpc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.netty.rpc.protocol.MessageCodecSharable;
import org.masteryourself.tutorial.netty.rpc.protocol.ProcotolFrameDecoder;
import org.masteryourself.tutorial.netty.rpc.server.handler.ServerRequestHandler;

/**
 * <p>description : RpcServer
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/18 12:30 AM
 */
@Slf4j
public class RpcServer {

    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.group(boss, worker);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ProcotolFrameDecoder());
                    ch.pipeline().addLast(MESSAGE_CODEC);
                    // 业务逻辑处理
                    ch.pipeline().addLast(new ServerRequestHandler());
//                    // 用来判断是不是 [读空闲时间过长]，或 [写空闲时间过长]
//                    // 10s 内如果没有收到 channel 的数据，会触发一个 IdleState#READER_IDLE 事件
//                    ch.pipeline().addLast(new IdleStateHandler(10, 0, 0));
//                    // ChannelDuplexHandler 可以同时作为入站和出站处理器
//                    ch.pipeline().addLast(new ChannelDuplexHandler() {
//                        // 用来触发特殊事件
//                        @Override
//                        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//                            IdleStateEvent event = (IdleStateEvent) evt;
//                            // 触发了读空闲事件
//                            if (IdleState.READER_IDLE == event.state()) {
//                                log.info("已经 10s 没有读取到数据了, 干掉连接");
//                                ctx.channel().close();
//                            }
//                        }
//                    });
                }
            });
            Channel channel = serverBootstrap.bind(8080).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("server error", e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
