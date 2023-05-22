package org.masteryourself.tutorial.designpattern.creatation.singleton;

/**
 * <p>description : SingletonTest
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/12 11:37 AM
 */
public class SingletonTest {

    public static void main(String[] args) {
        // 饿汉式
        HungrySingleton hungryInstance1 = HungrySingleton.getInstance();
        HungrySingleton hungryInstance2 = HungrySingleton.getInstance();
        System.out.println(hungryInstance1 == hungryInstance1);
        // 懒汉式
        LazySingleton lazyInstance1 = LazySingleton.getInstance();
        LazySingleton lazyInstance2 = LazySingleton.getInstance();
        System.out.println(lazyInstance1 == lazyInstance2);
    }

}
