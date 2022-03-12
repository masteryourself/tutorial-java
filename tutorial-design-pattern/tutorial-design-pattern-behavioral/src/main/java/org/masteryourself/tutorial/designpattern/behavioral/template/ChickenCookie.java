package org.masteryourself.tutorial.designpattern.behavioral.template;

/**
 * <p>description : ChickenCookie
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/12 11:58 AM
 */
public class ChickenCookie extends CookieTemplate{
    @Override
    public void addFood() {
        System.out.println("加入烧鸡");
    }

    @Override
    public void addSalt() {
        System.out.println("放了 2 勺盐");
    }
}
