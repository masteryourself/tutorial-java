package pers.masteryourself.tutorial.jvm.oom;

import java.util.ArrayList;

/**
 * <p>description : GCOverHeadDemo
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/5/3 14:55
 */
public class GCOverheadDemo {

    /**
     * VM 参数：-Xms10m -Xmx10m -XX:+PrintGCDetails
     * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
     *
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; ; i++) {
            list.add(String.valueOf(i));
        }
    }

}
