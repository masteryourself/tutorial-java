package org.masteryourself.tutorial.designpattern.structual.proxy.statics;

/**
 * <p>description : UserServiceProxy
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/27 7:08 PM
 */
public class UserServiceProxy implements UserService {

    private UserService target;

    public UserServiceProxy(UserService target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("执行方法前日志");
        this.target.save();
        System.out.println("执行方法后日志");
    }
}
