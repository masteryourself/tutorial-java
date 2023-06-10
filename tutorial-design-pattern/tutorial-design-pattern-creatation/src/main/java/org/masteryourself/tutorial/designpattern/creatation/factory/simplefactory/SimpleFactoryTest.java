package org.masteryourself.tutorial.designpattern.creatation.factory.simplefactory;

/**
 * <p>description : SimpleFactoryTest
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/10 12:07
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
        CoffeeStore coffeeStore = new CoffeeStore();
        coffeeStore.orderCoffee("americano");
    }

}
