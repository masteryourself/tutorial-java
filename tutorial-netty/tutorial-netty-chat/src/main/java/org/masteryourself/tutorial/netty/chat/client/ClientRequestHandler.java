package org.masteryourself.tutorial.netty.chat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.masteryourself.tutorial.netty.chat.message.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>description : ClientRequestHandler
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/17 11:29 AM
 */
public class ClientRequestHandler extends ChannelInboundHandlerAdapter {

    private final CountDownLatch WAIT_FOR_LOGIN = new CountDownLatch(1);
    private final AtomicBoolean LOGIN_FLAG = new AtomicBoolean(false);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                System.out.println("请输入用户名:");
                String username = scanner.nextLine();
                System.out.println("请输入密码:");
                String password = scanner.nextLine();
                // 构造消息对象
                LoginRequestMessage message = new LoginRequestMessage(username, password);
                // 发送消息
                ctx.writeAndFlush(message);
                System.out.println("等待后续操作...");
                try {
                    WAIT_FOR_LOGIN.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 如果登录失败
                if (!LOGIN_FLAG.get()) {
                    System.out.println("登录失败, 系统退出");
                    ctx.channel().close();
                    return;
                }
                // 处理业务逻辑
                while (true) {
                    System.out.println("==================================");
                    System.out.println("send [username] [content]");
                    System.out.println("gsend [group name] [content]");
                    System.out.println("gcreate [group name] [m1,m2,m3...]");
                    System.out.println("gmembers [group name]");
                    System.out.println("gjoin [group name]");
                    System.out.println("gquit [group name]");
                    System.out.println("quit");
                    System.out.println("==================================");
                    String command = scanner.nextLine();
                    String[] s = command.split(" ");
                    switch (s[0]) {
                        case "send":
                            ctx.writeAndFlush(new ChatRequestMessage(username, s[1], s[2]));
                            break;
                        case "gsend":
                            ctx.writeAndFlush(new GroupChatRequestMessage(username, s[1], s[2]));
                            break;
                        case "gcreate":
                            Set<String> set = new HashSet<>(Arrays.asList(s[2].split(",")));
                            set.add(username); // 加入自己
                            ctx.writeAndFlush(new GroupCreateRequestMessage(s[1], set));
                            break;
                        case "gmembers":
                            ctx.writeAndFlush(new GroupMembersRequestMessage(s[1]));
                            break;
                        case "gjoin":
                            ctx.writeAndFlush(new GroupJoinRequestMessage(username, s[1]));
                            break;
                        case "gquit":
                            ctx.writeAndFlush(new GroupQuitRequestMessage(username, s[1]));
                            break;
                        case "quit":
                            ctx.channel().close();
                            return;
                    }
                }
            }
        }, "SYSTEM IN").start();
        // 继续调用 chain
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof LoginResponseMessage) {
            LoginResponseMessage response = (LoginResponseMessage) msg;
            if (response.isSuccess()) {
                LOGIN_FLAG.set(true);
            }
            WAIT_FOR_LOGIN.countDown();
        }
    }

}
