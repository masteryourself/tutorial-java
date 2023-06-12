package org.masteryourself.tutorial.netty.toolkit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : HandlerExecutorServe
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/11 12:57 PM
 */
@Slf4j
public class HandlerExecutorServe {

    public static void main(String[] args) {
        EventLoopGroup executors = new DefaultEventLoopGroup(2);
        new ServerBootstrap()
                // 1. 创建 NioEventLoopGroup，可以简单理解为 线程池 + Selector
                .group(new NioEventLoopGroup(), new NioEventLoopGroup())
                // 2. 选择服务 socket 实现类，其中 NioServerSocketChannel 表示基于 NIO 的服务器端实现
                .channel(NioServerSocketChannel.class)
                // 3. 添加 handler 处理器
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        // 5. SocketChannel 的处理器，解码 ByteBuf => String
                        ch.pipeline().addLast(new StringDecoder());
                        // 6. 指定一个异步线程去处理
                        ch.pipeline().addLast(executors, "custom", new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
                                log.info("收到消息 {}", msg);
                            }
                        });
                    }
                })
                // 4. ServerSocketChannel 绑定的监听端口
                .bind(9527);
    }

}
