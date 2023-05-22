package org.masteryourself.tutorial.designpattern.structual.proxy.statics;

/**
 * <p>description : StaticsProxy
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/27 7:09 PM
 */
public class StaticsProxyTest {

    public static void main(String[] args) {
        new UserServiceProxy(new UserService.UserServiceImpl()).save();
    }

}
