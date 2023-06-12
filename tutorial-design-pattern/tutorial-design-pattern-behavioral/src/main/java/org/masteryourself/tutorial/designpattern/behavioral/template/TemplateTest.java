package org.masteryourself.tutorial.designpattern.behavioral.template;

/**
 * <p>description : TemplateTest
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/12 11:58 AM
 */
public class TemplateTest {

    public static void main(String[] args) {
        CookieTemplate chicken = new ChickenCookie();
        chicken.cookie();
    }

}
