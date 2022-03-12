package org.masteryourself.tutorial.designpattern.behavioral.mediator;

/**
 * <p>description : MediatorTest
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/12 1:56 PM
 */
public class MediatorTest {

    public static void main(String[] args) {
        ControllerTower tower = new ControllerTower();
        Captain sc8633 = new Captain.SC8633(tower);
        Captain bj9627 = new Captain.BJ9627(tower);
        // sc8633 请求起飞 > 允许
        sc8633.fly();
        // bj9627 请求降落 > 不允许
        bj9627.land();
        // sc8633 起飞完成
        sc8633.success();
        // bj9627 请求降落 > 允许
        bj9627.land();
    }

}
