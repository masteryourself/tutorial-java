package org.masteryourself.tutorial.netty.chat.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.masteryourself.tutorial.netty.chat.message.LoginRequestMessage;
import org.masteryourself.tutorial.netty.chat.message.LoginResponseMessage;
import org.masteryourself.tutorial.netty.chat.server.service.UserServiceFactory;

/**
 * <p>description : LoginRequestHandler
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/17 11:10 AM
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) throws Exception {
        String userName = msg.getUsername();
        String password = msg.getPassword();
        boolean login = UserServiceFactory.getUserService().login(userName, password);
        if (login) {
            ctx.writeAndFlush(new LoginResponseMessage(true, "登录成功"));
        } else {
            ctx.writeAndFlush(new LoginResponseMessage(false, "登录失败"));
        }
    }

}
