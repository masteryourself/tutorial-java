package org.masteryourself.tutorial.designpattern.creatation.factory.abstractfactory;

/**
 * <p>description : IFactory
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/26 5:46 PM
 */
public interface IFactory {

    default Car createCar() {
        return null;
    }

    default Pen createPen() {
        return null;
    }

    /**
     * 抽象工厂创建的汽车抽象工厂
     */
    interface CarFactory extends IFactory {

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

    /**
     * 抽象工厂创建的笔抽象工厂
     */
    interface PenFactory extends IFactory {

        class PencilFactory implements PenFactory {

            @Override
            public Pen createPen() {
                return new Pen.Pencil();
            }
        }

        class BallPenFactory implements PenFactory {

            @Override
            public Pen createPen() {
                return new Pen.BallPen();
            }
        }

    }

}
