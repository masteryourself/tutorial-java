package pers.masteryourself.tutorial.jvm.reference;

import java.util.WeakHashMap;

/**
 * <p>description : WeakHashMap 使用
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
        Integer k1 = 1;
        Integer k2 = new Integer(2);
        weakHashMap.put(k1, "zs");
        weakHashMap.put(k2, "ls");
        // {2=ls, 1=zs}
        System.out.println(weakHashMap);
        k1 = null;
        k2 = null;
        System.gc();
        // {1=zs}
        System.out.println(weakHashMap);
    }

}
