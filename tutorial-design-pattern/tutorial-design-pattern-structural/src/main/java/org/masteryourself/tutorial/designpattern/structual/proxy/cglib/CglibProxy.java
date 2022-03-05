package org.masteryourself.tutorial.designpattern.structual.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

/**
 * <p>description : CglibProxy
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/27 7:11 PM
 */
public class CglibProxy {

    public static <T> T getProxy(T t) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(t.getClass());
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] args) throws Throwable {
                System.out.println("执行方法前日志");
                Object res = method.invoke(t, args);
                System.out.println("执行方法后日志");
                return res;
            }
        });
        return (T) enhancer.create();
    }

}
