package org.masteryourself.tutorial.netty.chat.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.masteryourself.tutorial.netty.chat.message.ChatRequestMessage;
import org.masteryourself.tutorial.netty.chat.message.ChatResponseMessage;
import org.masteryourself.tutorial.netty.chat.server.session.SessionFactory;

/**
 * <p>description : ChatRequestMessageHandler
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/17 11:56 AM
 */
@ChannelHandler.Sharable
public class ChatRequestMessageHandler extends SimpleChannelInboundHandler<ChatRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatRequestMessage msg) throws Exception {
        String to = msg.getTo();
        Channel channel = SessionFactory.getSession().getChannel(to);
        if (channel != null) {
            // 在线就发送给对方
            channel.writeAndFlush(new ChatResponseMessage(msg.getFrom(), msg.getContent()));
            return;
        }
        // 不在线就提醒给发送人
        ctx.writeAndFlush(new ChatResponseMessage(false, "对方用户不在线"));
    }

}
