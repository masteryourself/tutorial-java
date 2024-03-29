package org.masteryourself.tutorial.designpattern.creatation.factory.factorymethod;


/**
 * <p>description : FactoryMethodTest
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/26 5:21 PM
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
        CoffeeStore coffeeStore = new CoffeeStore(new CoffeeFactory.LatteCoffeeFactory());
        coffeeStore.orderCoffee();
    }

}
