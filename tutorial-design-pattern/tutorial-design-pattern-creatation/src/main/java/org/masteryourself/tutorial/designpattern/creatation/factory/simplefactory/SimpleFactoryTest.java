package org.masteryourself.tutorial.designpattern.creatation.factory.simplefactory;

/**
 * <p>description : SimpleFactoryTest
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/26 5:06 PM
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
        CarFactory.createCar("奥迪").run();
        CarFactory.createCar("吉利").run();
    }

}
