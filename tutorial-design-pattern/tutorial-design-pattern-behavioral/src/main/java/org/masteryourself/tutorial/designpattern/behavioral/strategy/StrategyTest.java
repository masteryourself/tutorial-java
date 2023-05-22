package org.masteryourself.tutorial.designpattern.behavioral.strategy;

/**
 * <p>description : StrategyTest
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/12 12:09 PM
 */
public class StrategyTest {

    public static void main(String[] args) {
        Team team = new Team(new GameStrategy.GrowStrategy());
        team.game();
    }

}
