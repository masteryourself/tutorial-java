package org.masteryourself.tutorial.designpattern.behavioral.mediator;

/**
 * <p>description : ControllerTower
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/12 1:50 PM
 */
public class ControllerTower {

    private boolean canDo = true;

    public void accept(Captain captain, String action) {
        if ("fly".equals(action) || "land".equals(action)) {
            if (canDo) {
                System.out.println(captain.getName() + " >> 允许");
                canDo = false;
            } else {
                System.out.println(captain.getName() + " >> 不允许");
            }
        } else {
            System.out.println(captain.getName() + " >> 收到通知");
            canDo = true;
        }
    }

}
