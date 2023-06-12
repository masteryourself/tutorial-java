package org.masteryourself.tutorial.jvm.heap;

/**
 * <p>description : 堆内存排查工具使用
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/30 3:37 PM
 */
public class HeapMemoryTool {

    public static void main(String[] args) throws Exception {
        // 1: 初始内存信息
        System.out.println(1);
        Thread.sleep(30000);
        // 2. 分配了 10m 内存之后的信息
        byte[] bytes = new byte[10 * 1024 * 1024];
        System.out.println(2);
        Thread.sleep(30000);
        // 3. 调用 gc 清理之后的信息
        bytes = null;
        System.gc();
        System.out.println(3);
        Thread.sleep(30000);
    }

}
