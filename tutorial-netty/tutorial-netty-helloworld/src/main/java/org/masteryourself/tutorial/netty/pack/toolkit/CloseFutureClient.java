package org.masteryourself.tutorial.netty.pack.toolkit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : CloseFutureClient
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/11 3:27 PM
 */
@Slf4j
public class CloseFutureClient {

    public static void main(String[] args) throws Exception {
        NioEventLoopGroup executors = new NioEventLoopGroup();
        ChannelFuture channelFuture = new Bootstrap()
                .group(executors)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect("127.0.0.1", 9527);
        channelFuture.sync();
        Channel channel = channelFuture.channel();
        channel.writeAndFlush("连接建立成功");
        channel.close();
        // 1. 同步: 等待关闭通道
        // channel.closeFuture().sync();
        // log.info("通道已经关闭");
        // executors.shutdownGracefully();
        // 2. 异步: 使用 ChannelFutureListener 等待回调
        ChannelFutureListener channelFutureListener = new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                log.info("通道已经关闭");
                executors.shutdownGracefully();
            }
        };
        channel.closeFuture().addListener(channelFutureListener);
    }

}
