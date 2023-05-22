package org.masteryourself.tutorial.designpattern.creatation.factory.simplefactory;

/**
 * <p>description : CarFactory
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/26 5:03 PM
 */
public class CarFactory {

    /**
     * 简单工厂三个角色
     * Factory: 工厂角色
     * Product: 抽象产品
     * ConcreteProduct: 具体产品角色
     * <p>
     * 缺点: 违背开闭，扩展不易
     */
    public static Car createCar(String name) {
        if (name.equals("奥迪")) {
            return new Car.AoDi();
        }
        if (name.equals("吉利")) {
            return new Car.JiLi();
        }
        return null;
    }

}
