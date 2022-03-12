package org.masteryourself.tutorial.designpattern.behavioral.strategy;

/**
 * <p>description : GameStrategy
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/12 12:07 PM
 */
public interface GameStrategy {

    void doStrategy();

    class FightStrategy implements GameStrategy {

        @Override
        public void doStrategy() {
            System.out.println("打架运营");
        }
    }

    class GrowStrategy implements GameStrategy {

        @Override
        public void doStrategy() {
            System.out.println("猥琐发育运营");
        }
    }

}
