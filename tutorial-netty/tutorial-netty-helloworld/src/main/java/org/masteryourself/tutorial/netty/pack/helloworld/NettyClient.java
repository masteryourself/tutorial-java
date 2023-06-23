package org.masteryourself.tutorial.netty.pack.helloworld;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

/**
 * <p>description : NettyClient
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/11 12:57 PM
 */
public class NettyClient {

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
        // 5. Netty 中很多方法都是异步的，如 connect，这时需要使用 sync 方法等待 connect 建立连接完毕
        channelFuture.sync();
        // 6. 获取 channel 对象，它即为通道抽象，可以进行数据读写操作
        Channel channel = channelFuture.channel();
        // 7. 写入消息并清空缓冲区
        channel.writeAndFlush(new Date() + ":hello world!");
    }

}
