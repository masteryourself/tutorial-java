package pers.masteryourself.tutorial.jvm.gc.fgc;

/**
 * <p>description : FullGCDemo，产生 YGC 与 FGC
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/5/3 11:21
 */
public class FullGCDemo {

    /**
     * -Xms10m -Xmx10m -XX:PrintGCDetails
     *
     * @param args
     */
    public static void main(String[] args) {
        byte[] bytes = new byte[10 * 1024 * 1024];
    }

}
