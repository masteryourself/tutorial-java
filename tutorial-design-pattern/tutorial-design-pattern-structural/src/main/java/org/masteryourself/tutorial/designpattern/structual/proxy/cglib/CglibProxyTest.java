package org.masteryourself.tutorial.designpattern.structual.proxy.cglib;

import org.masteryourself.tutorial.designpattern.structual.proxy.jdk.JdkProxy;
import org.masteryourself.tutorial.designpattern.structual.proxy.jdk.UserService;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * <p>description : CglibProxyTest
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/5 1:29 PM
 */
public class CglibProxyTest {

    public static void main(String[] args) {
        UserService proxy = CglibProxy.getProxy(new UserService.UserServiceImpl());
        proxy.save();
        // class org.masteryourself.tutorial.designpattern.structual.proxy.jdk.UserService$UserServiceImpl$$EnhancerByCGLIB$$9ee3c719
        System.out.println(proxy.getClass());
        // [interface net.sf.cglib.proxy.Factory]
        System.out.println(Arrays.toString(proxy.getClass().getInterfaces()));
        // class org.masteryourself.tutorial.designpattern.structual.proxy.jdk.UserService$UserServiceImpl
        System.out.println(proxy.getClass().getSuperclass());
        // true
        System.out.println(proxy instanceof UserService);
        // true
        System.out.println(proxy instanceof UserService.UserServiceImpl);
        // false
        System.out.println(proxy instanceof Proxy);
    }

}
