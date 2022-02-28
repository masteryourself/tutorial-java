package org.masteryourself.tutorial.designpattern.creatation.factory.factorymethod;

/**
 * <p>description : CarFactory
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/26 5:03 PM
 */
public interface CarFactory {

    /**
     * 工厂方法四个角色
     * Product: 抽象产品
     * ConcreteProduct: 具体产品角色
     * Factory: 抽象工厂
     * ConcreteFactory：具体工厂
     * <p>
     * 缺点: 系统复杂度增加，产品单一
     */
    Car createCar();

    class AoDiFactory implements CarFactory {

        @Override
        public Car createCar() {
            return new Car.AoDi();
        }
    }

    class JiLiFactory implements CarFactory {

        @Override
        public Car createCar() {
            return new Car.JiLi();
        }
    }

}
