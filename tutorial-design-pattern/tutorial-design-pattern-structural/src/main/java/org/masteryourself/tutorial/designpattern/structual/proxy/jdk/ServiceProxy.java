package org.masteryourself.tutorial.designpattern.structual.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>description : ServiceProxy
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/27 7:11 PM
 */
public class ServiceProxy {

    public static <T> Object getProxy(T t) {
        return Proxy.newProxyInstance(
                t.getClass().getClassLoader(),
                t.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("执行方法前日志");
                    Object res = method.invoke(t, args);
                    System.out.println("执行方法前日志");
                    return res;
                });
    }

}
