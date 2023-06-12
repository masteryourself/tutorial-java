package org.masteryourself.tutorial.jvm.classloader.custom;

/**
 * <p>description : CustomClassLoaderTest
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/5 12:02 PM
 */
public class CustomClassLoaderTest {

    public static void main(String[] args) throws Exception{
        CustomClassLoader loader1 = new CustomClassLoader();
        Class<?> b1 = loader1.loadClass("B");
        Class<?> b2 = loader1.loadClass("B");
        CustomClassLoader loader2 = new CustomClassLoader();
        Class<?> b3 = loader2.loadClass("B");
        // true
        System.out.println(b1 == b2);
        // false
        System.out.println(b1==b3);
    }

}
