package pers.masteryourself.tutorial.jvm.oom;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : UnableCreateNewThreadDemo
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/5/3 15:37
 */
public class UnableCreateNewThreadDemo {

    /**
     * Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
     *
     * @param args
     */
    public static void main(String[] args) {
        while (true) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
