package org.masteryourself.tutorial.designpattern.creatation.singleton;

/**
 * <p>description : 饿汉式
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/26 2:13 PM
 */
public class HungrySingleton {

    private static final HungrySingleton INSTANCE = new HungrySingleton();

    private HungrySingleton() {

    }

    public static HungrySingleton getInstance() {
        return INSTANCE;
    }

}
