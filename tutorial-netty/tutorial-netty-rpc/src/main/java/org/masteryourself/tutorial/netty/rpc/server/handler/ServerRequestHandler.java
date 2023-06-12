package org.masteryourself.tutorial.netty.rpc.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.netty.rpc.message.RpcRequestMessage;
import org.masteryourself.tutorial.netty.rpc.message.RpcResponseMessage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>description : ServerRequestHandler
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/18 1:04 AM
 */
@Slf4j
public class ServerRequestHandler extends SimpleChannelInboundHandler<RpcRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequestMessage msg) throws Exception {
        RpcResponseMessage response = new RpcResponseMessage();
        response.setSequenceId(msg.getSequenceId());
        try {
            // 通过反射调用目标方法
            Object serverImpl = ServicesFactory.getService(Class.forName(msg.getInterfaceName()));
            Method method = serverImpl.getClass().getMethod(msg.getMethodName(), msg.getParameterTypes());
            Object ret = method.invoke(serverImpl, msg.getParameterValue());
            response.setReturnValue(ret);
        } catch (Throwable e) {
            if (e instanceof InvocationTargetException) {
                response.setExceptionValue(((InvocationTargetException) e).getTargetException());
            } else {
                response.setExceptionValue(e);
            }
        } finally {
            // 将响应写回
            ctx.writeAndFlush(response);
        }
    }

}
