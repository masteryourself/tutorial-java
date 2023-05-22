package org.masteryourself.tutorial.designpattern.creatation.factory.abstractfactory;

/**
 * <p>description : Pen
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/26 5:40 PM
 */
public interface Pen {

    void write();

    class Pencil implements Pen {

        @Override
        public void write() {
            System.out.println("用铅笔写字");
        }
    }

    class BallPen implements Pen {

        @Override
        public void write() {
            System.out.println("用圆珠笔写字");
        }
    }

}
