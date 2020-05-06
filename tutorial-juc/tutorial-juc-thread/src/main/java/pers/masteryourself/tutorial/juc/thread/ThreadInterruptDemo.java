package pers.masteryourself.tutorial.juc.thread;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : ThreadInterruptDemo, 线程
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/5/6 15:13
 */
public class ThreadInterruptDemo {

    public static void main(String[] args) throws Exception {
        threadInterrupted();
        interruptedExceptionReset();
    }

    /**
     * Thread.interrupted() 设置复位
     *
     * @throws InterruptedException
     */
    private static void threadInterrupted() throws InterruptedException {
        Thread thred = new Thread(() -> {
            while (true) {
                boolean in = Thread.currentThread().isInterrupted();
                if (in) {
                    // 调用 Thread.interrupted() 之前:true
                    System.out.println("调用 Thread.interrupted() 之前:" + in);
                    Thread.interrupted();
                    // 调用 Thread.interrupted() 之后:false
                    System.out.println("调用 Thread.interrupted() 之后:" + Thread.currentThread().isInterrupted());
                }
            }
        });
        thred.start();
        TimeUnit.SECONDS.sleep(1);
        // Just to set the interrupt flag, 只是设置 interrupt 标识为 true, 并不是中断线程
        thred.interrupt();
    }

    /**
     * 抛出 {@link InterruptedException} 异常后, 中断标识复位
     *
     * @throws InterruptedException
     */
    private static void interruptedExceptionReset() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        // 正常运行:false
        System.out.println("正常运行:" + thread.isInterrupted());
        thread.interrupt();
        // 调用 interrupt() 后:true
        System.out.println("调用 interrupt() 后:" + thread.isInterrupted());
        TimeUnit.SECONDS.sleep(1);
        // 抛出 InterruptedException 异常之后，线程标识复位:false
        System.out.println("抛出 InterruptedException 异常之后，线程标识复位:" + thread.isInterrupted());
    }

}
