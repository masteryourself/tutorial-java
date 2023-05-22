package org.masteryourself.tutorial.designpattern.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>description : AbstractAnchor
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/12 2:39 PM
 */
public class AbstractAnchor {

    private List<AbstractFans> fansList = new ArrayList<>();

    public void addFans(AbstractFans fans) {
        fansList.add(fans);
    }

    public void notifyFans(String msg) {
        for (AbstractFans fans : fansList) {
            fans.receiveMsg(msg);
        }
    }

    public static class BeautyAnchor extends AbstractAnchor {

        public void startSell() {
            System.out.println("美女主播开始直播带货, 通知粉丝");
            notifyFans("家人们, 疯狂买买买");
        }

        public void endSell() {
            System.out.println("美女主播卖货结束, 通知粉丝");
            notifyFans("家人们, 本次直播到此为止, 拜拜");
        }

    }

}
