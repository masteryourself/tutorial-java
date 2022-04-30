package org.masteryourself.tutorial.jvm.stringtable;

/**
 * <p>description : StringTableGarbageCollection
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/30 11:35 PM
 */
public class StringTableGarbageCollection {

    /**
     * 添加 VM 参数 -Xmx10m -XX:+PrintStringTableStatistics -XX:+PrintGCDetails -verbose:gc
     */
    public static void main(String[] args) {
        try {
            for (int j = 0; j < 10000; j++) {
                String.valueOf(j).intern();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
