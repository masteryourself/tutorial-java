package org.masteryourself.tutorial.jvm.classloader.boot;

/**
 * <p>description : BootClassLoaderDemo
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/5 10:53 AM
 */
public class BootClassLoaderDemo {

    public static void main(String[] args) throws Exception {
        Class<?> a = Class.forName("org.masteryourself.tutorial.jvm.classloader.boot.A");
        System.out.println(a.getClassLoader());
    }

}
