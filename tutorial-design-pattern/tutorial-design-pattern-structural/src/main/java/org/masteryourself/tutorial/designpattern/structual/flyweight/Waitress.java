package org.masteryourself.tutorial.designpattern.structual.flyweight;

import lombok.AllArgsConstructor;

/**
 * <p>description : Waitress
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/5 2:52 PM
 */
public interface Waitress {

    // 享元的不可共享属性留给外部进行实现
    void start(String customer);

    // 享元的不可共享属性留给外部进行实现
    void stop(String customer);

    boolean state();

    @AllArgsConstructor
    class WaitressImpl implements Waitress {

        // 共享属性
        private String id;
        private String name;
        private Integer age;

        // 不可共享属性
        private boolean state;

        @Override
        public void start(String customer) {
            System.out.println(this.name + "为顾客[" + customer + "]开始工作");
            this.state = false;
        }

        @Override
        public void stop(String customer) {
            System.out.println(this.name + "为顾客[" + customer + "]结束工作");
            this.state = true;
        }

        @Override
        public boolean state() {
            return this.state;
        }

    }

}
