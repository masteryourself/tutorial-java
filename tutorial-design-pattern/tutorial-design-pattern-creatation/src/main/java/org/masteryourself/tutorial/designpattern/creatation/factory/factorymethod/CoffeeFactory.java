package org.masteryourself.tutorial.designpattern.creatation.factory.factorymethod;

/**
 * <p>description : CoffeeFactory
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/10 12:08
 */
public interface CoffeeFactory {

    Coffee createCoffee();

    class AmericanCoffeeFactory implements CoffeeFactory {

        @Override
        public Coffee createCoffee() {
            return new Coffee.AmericanCoffee();
        }
    }

    class LatteCoffeeFactory implements CoffeeFactory {

        @Override
        public Coffee createCoffee() {
            return new Coffee.LatteCoffee();
        }
    }

}
