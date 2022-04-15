package org.masteryourself.tutorial.netty.lengthfieldbased;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * <p>description : LengthFieldBasedNettyClient
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/15 6:18 PM
 */
@Slf4j
public class LengthFieldBasedNettyClient {

    public static void main(String[] args) {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            ChannelFuture channelFuture = new Bootstrap()
                    .group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    int length = new Random().nextInt(200) + 1000;
                                    log.info("客户端一共准备发送 {} 个字节", length);
                                    char init = 'a';
                                    ByteBuf buffer = ctx.alloc().buffer();
                                    // 先写入 4 个字节的长度
                                    buffer.writeInt(length);
                                    for (int i = 0; i < length; i++) {
                                        if (init == 'z') {
                                            init = 'a';
                                        }
                                        buffer.writeByte(init++);
                                    }
                                    // 客户端发送一次数据, 希望服务端也接收一次数据
                                    ctx.writeAndFlush(buffer);
                                    // 发送完成之后关闭 channel
                                    ctx.close();
                                }
                            });
                        }
                    })
                    .connect("127.0.0.1", 9527);
            channelFuture.sync();
            Channel channel = channelFuture.channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

}
