package org.masteryourself.tutorial.designpattern.behavioral.template;

/**
 * <p>description : CookieTemplate
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/12 11:49 AM
 */
public abstract class CookieTemplate {

    public void cookie() {
        fire();
        addFood();
        addSalt();
        end();
    }

    public void fire() {
        System.out.println("开火加油");
    }

    public abstract void addFood();

    public abstract void addSalt();

    public void end() {
        System.out.println("关火装盘");
    }

}
