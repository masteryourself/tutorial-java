package org.masteryourself.tutorial.designpattern.creatation.factory.simplefactory;

/**
 * <p>description : SimpleCoffeeFactory
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/10 11:49
 */
public class CoffeeFactory {

    public static Coffee createCoffee(String type) {
        Coffee coffee = null;
        if ("americano".equals(type)) {
            coffee = new Coffee.AmericanCoffee();
        } else if ("latte".equals(type)) {
            coffee = new Coffee.LatteCoffee();
        }
        return coffee;
    }

}
