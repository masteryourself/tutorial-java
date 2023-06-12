package org.masteryourself.tutorial.designpattern.behavioral.observer;

/**
 * <p>description : AbstractFans
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/12 2:40 PM
 */
public abstract class AbstractFans {

    public void follow(AbstractAnchor anchor) {
        anchor.addFans(this);
    }

    public abstract void receiveMsg(String msg);

    public static class PeopleFans extends AbstractFans {

        public void receiveMsg(String msg) {
            System.out.println("用户粉 收到主播对你说的悄悄话：" + msg);
        }

    }

    public static class RobotFans extends AbstractFans {

        @Override
        public void receiveMsg(String msg) {
            System.out.println("机器粉忽略");
        }
    }

}
