package pers.masteryourself.tutorial.juc.volatile_;

/**
 * <p>description : VolatileDemo
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/5/6 16:35
 */
public class SwitchFlag implements Runnable {

    public volatile boolean flag = true;

    @Override
    public void run() {
        while (flag) {
        }
        System.out.println("线程停止");
    }

    public static void main(String[] args) throws Exception {
        SwitchFlag switchFlag = new SwitchFlag();
        new Thread(switchFlag).start();
        Thread.sleep(3000);
        switchFlag.flag = false;
        System.out.println("flag 已经设置成 false");
        Thread.sleep(1000);
        System.out.println(switchFlag.flag);
    }

}
