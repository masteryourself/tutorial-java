package org.masteryourself.tutorial.netty.pack.chat.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.masteryourself.tutorial.netty.pack.chat.message.GroupJoinRequestMessage;
import org.masteryourself.tutorial.netty.pack.chat.message.GroupJoinResponseMessage;
import org.masteryourself.tutorial.netty.pack.chat.server.session.Group;
import org.masteryourself.tutorial.netty.pack.chat.server.session.GroupSessionFactory;

/**
 * <p>description : GroupJoinRequestMessageHandler
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/17 12:18 PM
 */
@ChannelHandler.Sharable
public class GroupJoinRequestMessageHandler extends SimpleChannelInboundHandler<GroupJoinRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupJoinRequestMessage msg) throws Exception {
        Group group = GroupSessionFactory.getGroupSession().joinMember(msg.getGroupName(), msg.getUsername());
        if (group == null) {
            ctx.writeAndFlush(new GroupJoinResponseMessage(true, msg.getGroupName() + "群不存在"));
            return;
        }
        ctx.writeAndFlush(new GroupJoinResponseMessage(true, msg.getGroupName() + "群加入成功"));
    }

}
