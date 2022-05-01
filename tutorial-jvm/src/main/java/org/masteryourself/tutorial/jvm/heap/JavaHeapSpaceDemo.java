package org.masteryourself.tutorial.jvm.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>description : 堆内存溢出案例
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/30 3:28 PM
 */
public class JavaHeapSpaceDemo {

    /**
     * 添加 VM 参数 -Xmx8m
     */
    public static void main(String[] args) {
        try {
            List<String> list = new ArrayList<>();
            String str = "hello";
            while (true) {
                list.add(str);
                str = str + str;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
