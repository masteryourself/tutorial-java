package pers.masteryourself.tutorial.jvm.oom;

/**
 * <p>description : JavaHeapSpaceDemo
 * <p>字符串常量池被撑爆导致 OOM
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/5/3 14:41
 */
public class JavaHeapSpaceDemo {

    /**
     * VM 参数：-Xms10m -Xmx10m
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     *
     * @param args
     */
    public static void main(String[] args) {
        byte[] bytes = new byte[10 * 1024 * 1024];
    }

}
