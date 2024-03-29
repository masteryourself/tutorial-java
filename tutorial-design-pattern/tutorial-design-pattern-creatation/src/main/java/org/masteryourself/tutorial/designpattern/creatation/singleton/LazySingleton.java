package org.masteryourself.tutorial.designpattern.creatation.singleton;

/**
 * <p>description : 懒汉式 + DCL
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/26 2:16 PM
 */
public class LazySingleton {

    // 防止指令重排序
    private static volatile LazySingleton instance;

    private LazySingleton() {

    }

    public static LazySingleton getInstance() {
        // 这里需要 double-check-lock
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

}
