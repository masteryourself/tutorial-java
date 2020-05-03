package pers.masteryourself.tutorial.jvm.reference;

import java.util.WeakHashMap;

/**
 * <p>description : WeakHashMap 使用
 * <p>使用 {@link WeakHashMap} 来保存缓存数据，一旦发生 GC，JVM 就会自动回收这些缓存数据，避免 OOM 情况
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/5/2 1:11
 */
public class WeakHashMapDemo {

    public static void main(String[] args) {
        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap<>();
        Integer k1 = new Integer(1);
        Integer k2 = new Integer(2);
        weakHashMap.put(k1, "zs");
        weakHashMap.put(k2, "ls");
        // {2=ls, 1=zs}
        System.out.println(weakHashMap);
        k1 = null;
        k2 = null;
        System.gc();
        // {}
        System.out.println(weakHashMap);
    }

}
