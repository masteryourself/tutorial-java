package org.masteryourself.tutorial.netty.toolkit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

/**
 * <p>description : ChannelListenerClient
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/11 12:57 PM
 */
public class ChannelListenerClient {

    public static void main(String[] args) throws Exception {
        ChannelFuture channelFuture = new Bootstrap()
                // 1. 创建 NioEventLoopGroup，同 Server
                .group(new NioEventLoopGroup())
                // 2. 选择服务 socket 实现类，其中 NioServerSocketChannel 表示基于 NIO 的服务器端实现
                .channel(NioSocketChannel.class)
                // 3. 添加 handler 处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 8. 消息会经过通道 handler 处理，这里是将 String => ByteBuf 发出
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                // 4. 指定要连接的服务器和端口
                .connect("127.0.0.1", 9527);
        // 5. 使用监听器回调
        channelFuture.addListener((ChannelFutureListener) future ->
                // 6. 此时 channel 已经建立好，发送消息
                future.channel().writeAndFlush(new Date() + ":hello world!")
        );
    }

}
