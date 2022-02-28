package org.masteryourself.tutorial.designpattern.structual.proxy.jdk;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * <p>description : JdkProxyTest
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/27 7:15 PM
 */
public class JdkProxyTest {

    /**
     * 说明 jdk 的类是继承了 Proxy 并实现了 UserService 接口
     * 所以 jdk 动态代理只支持转成接口类型, 不支持转成实例 (UserServiceImpl) 类型
     * $Proxy0 extends java.lang.reflect.Proxy implements UserService
     */
    public static void main(String[] args) {
        Object proxy = ServiceProxy.getProxy(new UserService.UserServiceImpl());
        ((UserService) proxy).save();
        // class com.sun.proxy.$Proxy0
        System.out.println(proxy.getClass());
        // [interface org.masteryourself.tutorial.designpattern.structual.proxy.jdk.UserService]
        System.out.println(Arrays.toString(proxy.getClass().getInterfaces()));
        // class java.lang.reflect.Proxy
        System.out.println(proxy.getClass().getSuperclass());
        // true
        System.out.println(proxy instanceof UserService);
        // false
        System.out.println(proxy instanceof UserService.UserServiceImpl);
        // true
        System.out.println(proxy instanceof Proxy);
    }

}
