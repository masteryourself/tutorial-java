package pers.masteryourself.tutorial.jvm.gc;

/**
 * <p>description : GCDemo
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/5/3 17:50
 */
public class GCDemo {

    /**
     * 1. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseSerialGC                  DefNew + Tenured
     * 2. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseParNewGC                  ParNew + Tenured，会出现警告，不建议这种配置
     * 3. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseParallelGC                PSYoungGen + ParOldGen
     * 4. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseParallelOldGC             PSYoungGen + ParOldGen
     * 5. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC           ParNew + CMS
     * 6. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseG1GC
     *
     * @param args
     */
    public static void main(String[] args) {
        byte[] bytes = new byte[10 * 1024 * 1024];
    }

}
