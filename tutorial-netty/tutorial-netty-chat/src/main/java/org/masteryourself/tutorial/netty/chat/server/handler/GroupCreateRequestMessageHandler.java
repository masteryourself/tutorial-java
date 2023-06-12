package org.masteryourself.tutorial.netty.chat.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.masteryourself.tutorial.netty.chat.message.GroupCreateRequestMessage;
import org.masteryourself.tutorial.netty.chat.message.GroupCreateResponseMessage;
import org.masteryourself.tutorial.netty.chat.server.session.Group;
import org.masteryourself.tutorial.netty.chat.server.session.GroupSessionFactory;
import org.masteryourself.tutorial.netty.chat.server.session.SessionFactory;

import java.util.Set;

/**
 * <p>description : GroupCreateRequestMessageHandler
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/17 12:07 PM
 */
@ChannelHandler.Sharable
public class GroupCreateRequestMessageHandler extends SimpleChannelInboundHandler<GroupCreateRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupCreateRequestMessage msg) throws Exception {
        String groupName = msg.getGroupName();
        Set<String> members = msg.getMembers();
        // 先判断名称是否重复
        Group group = GroupSessionFactory.getGroupSession().createGroup(groupName, members);
        if (group != null) {
            ctx.writeAndFlush(new GroupCreateResponseMessage(false, groupName + "已经存在"));
            return;
        }
        // 消息提醒
        for (String member : members) {
            Channel channel = SessionFactory.getSession().getChannel(member);
            if (channel != null) {
                channel.writeAndFlush(new GroupCreateResponseMessage(true, "您已被拉入" + groupName));
            }
        }
    }

}
