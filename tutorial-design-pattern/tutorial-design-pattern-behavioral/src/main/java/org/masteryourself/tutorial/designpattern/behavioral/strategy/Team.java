package org.masteryourself.tutorial.designpattern.behavioral.strategy;

/**
 * <p>description : Team
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/12 12:13 PM
 */
public class Team {

    private final GameStrategy gameStrategy;

    public Team(GameStrategy gameStrategy) {
        this.gameStrategy = gameStrategy;
    }

    public void game(){
        System.out.println("游戏开始");
        gameStrategy.doStrategy();
        System.out.println("游戏结束");
    }

}
