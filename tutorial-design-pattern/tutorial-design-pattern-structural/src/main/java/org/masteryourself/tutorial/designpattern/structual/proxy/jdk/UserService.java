package org.masteryourself.tutorial.designpattern.structual.proxy.jdk;

/**
 * <p>description : UserService
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/27 7:06 PM
 */
public interface UserService {

    void save();

    class UserServiceImpl implements UserService {

        @Override
        public void save() {
            System.out.println("保存用户");
        }
    }
}
