package org.masteryourself.tutorial.designpattern.creatation.factory.simplefactory;

/**
 * <p>description : CoffeeStore
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/10 11:49
 */
public class CoffeeStore {

    public Coffee orderCoffee(String type) {
        Coffee coffee = CoffeeFactory.createCoffee(type);
        //添加配料
        coffee.addMilk();
        coffee.addSugar();
        return coffee;
    }

}
