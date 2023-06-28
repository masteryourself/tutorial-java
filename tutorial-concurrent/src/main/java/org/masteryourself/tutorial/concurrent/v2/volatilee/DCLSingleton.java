package org.masteryourself.tutorial.concurrent.v2.volatilee;

/**
 * <p>description : DCLSingleton
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/28 11:41
 */
public class DCLSingleton {

    private DCLSingleton() {
    }

    private static DCLSingleton INSTANCE = null;

    // javap -v DCLSingleton.class
    public static DCLSingleton getInstance() {
        if (INSTANCE == null) {
            // 首次访问会同步，而之后的使用没有 synchronized
            synchronized (DCLSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DCLSingleton();
                }
            }
        }
        return INSTANCE;
    }

}
