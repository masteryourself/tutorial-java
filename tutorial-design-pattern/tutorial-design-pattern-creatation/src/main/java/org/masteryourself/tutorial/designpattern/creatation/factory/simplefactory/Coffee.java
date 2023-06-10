package org.masteryourself.tutorial.designpattern.creatation.factory.simplefactory;

/**
 * <p>description : Coffee
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/10 11:49
 */
public interface Coffee {

    String getName();

    void addMilk();

    void addSugar();

    class AmericanCoffee implements Coffee {

        @Override
        public String getName() {
            return "americanCoffee";
        }

        @Override
        public void addMilk() {
            System.out.println("AmericanCoffee...addMilk...");
        }

        @Override
        public void addSugar() {
            System.out.println("AmericanCoffee...addSuqar...");
        }
    }

    class LatteCoffee implements Coffee {

        @Override
        public String getName() {
            return "latteCoffee";
        }

        @Override
        public void addMilk() {
            System.out.println("LatteCoffee...addMilk...");
        }

        @Override
        public void addSugar() {
            System.out.println("LatteCoffee...addSuqar...");
        }
    }

}
