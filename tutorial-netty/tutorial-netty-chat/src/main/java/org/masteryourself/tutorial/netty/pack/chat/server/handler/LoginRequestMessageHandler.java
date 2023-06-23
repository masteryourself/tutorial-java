package org.masteryourself.tutorial.netty.pack.chat.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.masteryourself.tutorial.netty.pack.chat.message.LoginResponseMessage;
import org.masteryourself.tutorial.netty.pack.chat.server.service.UserServiceFactory;
import org.masteryourself.tutorial.netty.pack.chat.server.session.SessionFactory;
import org.masteryourself.tutorial.netty.pack.chat.message.LoginRequestMessage;

/**
 * <p>description : LoginRequestMessageHandler
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/17 11:10 AM
 */
@ChannelHandler.Sharable
public class LoginRequestMessageHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) throws Exception {
        String userName = msg.getUsername();
        String password = msg.getPassword();
        boolean login = UserServiceFactory.getUserService().login(userName, password);
        if (login) {
            // 存储到会话中
            SessionFactory.getSession().bind(ctx.channel(), userName);
            ctx.writeAndFlush(new LoginResponseMessage(true, "登录成功"));
            return;
        }
        ctx.writeAndFlush(new LoginResponseMessage(false, "登录失败"));
    }

}
