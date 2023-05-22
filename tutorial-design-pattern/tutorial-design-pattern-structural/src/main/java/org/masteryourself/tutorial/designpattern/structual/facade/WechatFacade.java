package org.masteryourself.tutorial.designpattern.structual.facade;

/**
 * <p>description : WeixinFacade
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/5 2:05 PM
 */
public class WechatFacade {

    public void handle(String name) {
        System.out.println("微信统一办事处(门面模式)");
        new Police().register(name);
        new Edu().assign(name);
        new Social().handleSocial(name);
    }

}
