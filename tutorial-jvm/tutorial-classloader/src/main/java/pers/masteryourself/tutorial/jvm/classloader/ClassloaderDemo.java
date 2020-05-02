package pers.masteryourself.tutorial.jvm.classloader;

/**
 * <p>description : ClassloaderDemo
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/5/2 18:04
 */
public class ClassloaderDemo {

    public static void main(String[] args) {
        bootstrapClassLoader();
        appClassLoader();
    }



    /**
     * Object 类在 rt.jar 中，由 Bootstrap ClassLoader 加载，所以这里是 null
     */
    private static void bootstrapClassLoader() {
        Object object = new Object();
        // null
        System.out.println(object.getClass().getClassLoader());
    }

    /**
     * 程序中定义的类由 {@link } 加载
     */
    private static void appClassLoader() {
        // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(ClassloaderDemo.class.getClassLoader());
        // sun.misc.Launcher$ExtClassLoader@4554617c
        System.out.println(ClassloaderDemo.class.getClassLoader().getParent());
        // null
        System.out.println(ClassloaderDemo.class.getClassLoader().getParent().getParent());
    }

}
