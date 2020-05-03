package pers.masteryourself.tutorial.jvm.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p>description : MetaSpaceOOMDemo
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/5/3 16:38
 */
public class MetaspaceOOMDemo {

    /**
     * VM 参数：-XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
     * Exception in thread "main" java.lang.OutOfMemoryError: Metaspace
     *
     * @param args
     */
    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MetaspaceOOMDemo.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return method.invoke(o, objects);
                }
            });
            enhancer.create();
        }
    }

}
