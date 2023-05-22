package org.masteryourself.tutorial.designpattern.structual.facade;

/**
 * <p>description : FacadeTest
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/5 2:08 PM
 */
public class FacadeTest {

    public static void main(String[] args) {
        new WechatFacade().handle("张三");
    }

}
