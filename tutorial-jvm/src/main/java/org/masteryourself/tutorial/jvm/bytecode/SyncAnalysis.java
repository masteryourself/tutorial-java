package org.masteryourself.tutorial.jvm.bytecode;

/**
 * <p>description : synchronized 字节码分析
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/4 2:26 PM
 */
public class SyncAnalysis {

    public static void main(String[] args) {
        Object lock = new Object();
        synchronized (lock) {
            System.out.println("ok");
        }
    }

}
