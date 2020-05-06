package pers.masteryourself.tutorial.juc.thread;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : InterruptDemo，设置线程中断标识
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/5/6 14:45
 */
public class InterruptDemo {

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(() -> {
            int i = 0;
            // 判断线程有没有终止
            while (!Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("最终的值是：" + i);
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        // Just to set the interrupt flag, 只是设置 interrupt 标识为 true, 并不是中断线程
        thread.interrupt();
        // true, 表示线程还未中断
        System.out.println(thread.isInterrupted());
    }

}
