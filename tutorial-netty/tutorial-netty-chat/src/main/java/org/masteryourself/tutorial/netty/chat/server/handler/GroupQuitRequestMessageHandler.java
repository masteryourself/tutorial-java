package org.masteryourself.tutorial.netty.chat.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.masteryourself.tutorial.netty.chat.message.GroupJoinResponseMessage;
import org.masteryourself.tutorial.netty.chat.message.GroupQuitRequestMessage;
import org.masteryourself.tutorial.netty.chat.server.session.Group;
import org.masteryourself.tutorial.netty.chat.server.session.GroupSessionFactory;
import org.masteryourself.tutorial.netty.chat.server.session.SessionFactory;

import java.util.Set;

/**
 * <p>description : GroupQuitRequestMessageHandler
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/17 12:20 PM
 */
@ChannelHandler.Sharable
public class GroupQuitRequestMessageHandler extends SimpleChannelInboundHandler<GroupQuitRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupQuitRequestMessage msg) throws Exception {
        Group group = GroupSessionFactory.getGroupSession().removeMember(msg.getGroupName(), msg.getUsername());
        if (group == null) {
            ctx.writeAndFlush(new GroupJoinResponseMessage(true, msg.getGroupName() + "群不存在"));
            return;
        }
        ctx.writeAndFlush(new GroupJoinResponseMessage(true, "已退出群" + msg.getGroupName()));
        // 通知其它人
        Set<String> members = GroupSessionFactory.getGroupSession().getMembers(msg.getGroupName());
        for (String member : members) {
            Channel channel = SessionFactory.getSession().getChannel(member);
            if (channel != null) {
                channel.writeAndFlush(new GroupJoinResponseMessage(true, "已退出群" + msg.getGroupName()));
            }
        }
    }

}
