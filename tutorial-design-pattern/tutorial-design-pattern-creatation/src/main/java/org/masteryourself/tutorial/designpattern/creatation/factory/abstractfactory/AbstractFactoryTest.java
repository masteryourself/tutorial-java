package org.masteryourself.tutorial.designpattern.creatation.factory.abstractfactory;

/**
 * <p>description : AbstractFactoryTest
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/26 5:50 PM
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {
        new IFactory.CarFactory.AoDiFactory().createCar().run();
        new IFactory.PenFactory.BallPenFactory().createPen().write();
    }

}
