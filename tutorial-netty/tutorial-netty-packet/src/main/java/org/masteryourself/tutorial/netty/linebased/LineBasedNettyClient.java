package org.masteryourself.tutorial.netty.linebased;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : LineBasedClient
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/15 5:49 PM
 */
@Slf4j
public class LineBasedNettyClient {

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
                                    ByteBuf buffer = ctx.alloc().buffer();
                                    for (int i = 0; i < 10; i++) {
                                        buffer.writeBytes(new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, '\n'});
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
