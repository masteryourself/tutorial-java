package org.masteryourself.tutorial.netty.chat.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.masteryourself.tutorial.netty.chat.message.GroupChatRequestMessage;
import org.masteryourself.tutorial.netty.chat.message.GroupChatResponseMessage;
import org.masteryourself.tutorial.netty.chat.server.session.GroupSessionFactory;
import org.masteryourself.tutorial.netty.chat.server.session.SessionFactory;

import java.util.Set;

/**
 * <p>description : GroupChatRequestMessageHandler
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/17 12:14 PM
 */
@ChannelHandler.Sharable
public class GroupChatRequestMessageHandler extends SimpleChannelInboundHandler<GroupChatRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupChatRequestMessage msg) throws Exception {
        Set<String> members = GroupSessionFactory.getGroupSession().getMembers(msg.getGroupName());
        for (String member : members) {
            Channel channel = SessionFactory.getSession().getChannel(member);
            if (channel != null) {
                channel.writeAndFlush(new GroupChatResponseMessage(msg.getFrom(), msg.getContent()));
            }
        }
    }

}
