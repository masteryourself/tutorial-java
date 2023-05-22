package org.masteryourself.tutorial.netty.protocol.redis;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : RedisProtocolNettyClient
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/15 7:25 PM
 */
@Slf4j
public class RedisProtocolNettyClient {

    private static final byte[] LINE = {13, 10};

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
                                    set(ctx);
                                    get(ctx);
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

    private static void get(ChannelHandlerContext ctx) {
        ByteBuf buf = ctx.alloc().buffer();
        buf.writeBytes("*2".getBytes());
        buf.writeBytes(LINE);
        buf.writeBytes("$3".getBytes());
        buf.writeBytes(LINE);
        buf.writeBytes("get".getBytes());
        buf.writeBytes(LINE);
        buf.writeBytes("$3".getBytes());
        buf.writeBytes(LINE);
        buf.writeBytes("aaa".getBytes());
        buf.writeBytes(LINE);
        ctx.writeAndFlush(buf);
    }

    private static void set(ChannelHandlerContext ctx) {
        ByteBuf buf = ctx.alloc().buffer();
        buf.writeBytes("*3".getBytes());
        buf.writeBytes(LINE);
        buf.writeBytes("$3".getBytes());
        buf.writeBytes(LINE);
        buf.writeBytes("set".getBytes());
        buf.writeBytes(LINE);
        buf.writeBytes("$3".getBytes());
        buf.writeBytes(LINE);
        buf.writeBytes("aaa".getBytes());
        buf.writeBytes(LINE);
        buf.writeBytes("$3".getBytes());
        buf.writeBytes(LINE);
        buf.writeBytes("bbb".getBytes());
        buf.writeBytes(LINE);
        ctx.writeAndFlush(buf);
    }

}
