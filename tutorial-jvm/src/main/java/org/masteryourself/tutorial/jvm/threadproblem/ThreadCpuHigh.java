package org.masteryourself.tutorial.jvm.threadproblem;

/**
 * <p>description : 某个 CPU 线程占用很高问题演示
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/30 2:55 PM
 */
public class ThreadCpuHigh {

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {

            }
        }).start();
    }

}
