package org.masteryourself.tutorial.designpattern.behavioral.strategy;

/**
 * <p>description : TravelStrategy
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/10 12:30
 */
public interface TravelStrategy {

    void travel();

    class Bicycle implements TravelStrategy {

        @Override
        public void travel() {
            System.out.println("选择自行车出行");
        }
    }

    class Car implements TravelStrategy {

        @Override
        public void travel() {
            System.out.println("选择汽车出行");
        }
    }

    class Train implements TravelStrategy {

        @Override
        public void travel() {
            System.out.println("选择火车出行");
        }
    }

    class Aircraft implements TravelStrategy {

        @Override
        public void travel() {
            System.out.println("选择飞机出行");
        }
    }

}
