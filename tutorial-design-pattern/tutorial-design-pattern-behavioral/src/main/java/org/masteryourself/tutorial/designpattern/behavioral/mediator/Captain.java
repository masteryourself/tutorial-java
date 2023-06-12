package org.masteryourself.tutorial.designpattern.behavioral.mediator;

/**
 * <p>description : Captain
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/12 1:49 PM
 */
public interface Captain {

    void land();

    void fly();

    void success();

    String getName();

    class SC8633 implements Captain {

        private ControllerTower tower;

        public SC8633(ControllerTower tower) {
            this.tower = tower;
        }

        @Override
        public void land() {
            tower.accept(this,"land");
        }

        @Override
        public void fly() {
            tower.accept(this,"fly");
        }

        @Override
        public void success() {
            tower.accept(this,"success");
        }

        @Override
        public String getName() {
            return "四川航空8633";
        }
    }

    class BJ9627 implements Captain {

        private ControllerTower tower;

        public BJ9627(ControllerTower tower) {
            this.tower = tower;
        }

        @Override
        public void land() {
            tower.accept(this,"land");
        }

        @Override
        public void fly() {
            tower.accept(this,"fly");
        }

        @Override
        public void success() {
            tower.accept(this,"success");
        }

        @Override
        public String getName() {
            return "北京航空9527";
        }

    }

}
