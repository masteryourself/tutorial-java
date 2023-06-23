package org.masteryourself.tutorial.netty.pack.chat.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.masteryourself.tutorial.netty.pack.chat.message.GroupMembersRequestMessage;
import org.masteryourself.tutorial.netty.pack.chat.message.GroupMembersResponseMessage;
import org.masteryourself.tutorial.netty.pack.chat.server.session.GroupSessionFactory;

import java.util.Set;

/**
 * <p>description : GroupMembersRequestMessageHandler
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/17 12:24 PM
 */
@ChannelHandler.Sharable
public class GroupMembersRequestMessageHandler extends SimpleChannelInboundHandler<GroupMembersRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMembersRequestMessage msg) throws Exception {
        Set<String> members = GroupSessionFactory.getGroupSession()
                .getMembers(msg.getGroupName());
        ctx.writeAndFlush(new GroupMembersResponseMessage(members));
    }

}
