package org.masteryourself.tutorial.netty.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.netty.chat.protocol.MessageCodecSharable;
import org.masteryourself.tutorial.netty.chat.protocol.ProcotolFrameDecoder;
import org.masteryourself.tutorial.netty.chat.server.handler.*;

@Slf4j
public class ChatServer {
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
                    ch.pipeline().addLast(new LoginRequestMessageHandler());
                    ch.pipeline().addLast(new ChatRequestMessageHandler());
                    ch.pipeline().addLast(new GroupCreateRequestMessageHandler());
                    ch.pipeline().addLast(new GroupChatRequestMessageHandler());
                    ch.pipeline().addLast(new GroupJoinRequestMessageHandler());
                    ch.pipeline().addLast(new GroupQuitRequestMessageHandler());
                    ch.pipeline().addLast(new GroupMembersRequestMessageHandler());
                    ch.pipeline().addLast(new QuitHandler());
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
