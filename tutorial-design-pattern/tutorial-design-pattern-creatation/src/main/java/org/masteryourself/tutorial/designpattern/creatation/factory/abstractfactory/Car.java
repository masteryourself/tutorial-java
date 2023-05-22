package org.masteryourself.tutorial.designpattern.creatation.factory.abstractfactory;

/**
 * <p>description : Car
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/26 5:04 PM
 */
public interface Car {

    void run();

    class AoDi implements Car {

        @Override
        public void run() {
            System.out.println("奥迪跑起来");
        }
    }

    class JiLi implements Car {

        @Override
        public void run() {
            System.out.println("吉利跑起来");
        }
    }

}

