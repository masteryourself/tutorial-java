package org.masteryourself.tutorial.designpattern.behavioral.observer;

/**
 * <p>description : ObServerTest
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/12 2:47 PM
 */
public class ObServerTest {

    public static void main(String[] args) {
        AbstractAnchor.BeautyAnchor anchor = new AbstractAnchor.BeautyAnchor();
        anchor.startSell();
        // 粉丝开始关注
        new AbstractFans.RobotFans().follow(anchor);
        new AbstractFans.RobotFans().follow(anchor);
        new AbstractFans.RobotFans().follow(anchor);
        new AbstractFans.PeopleFans().follow(anchor);
        anchor.endSell();
    }

}
