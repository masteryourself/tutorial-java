package org.masteryourself.tutorial.designpattern.behavioral.strategy;

/**
 * <p>description : TravelContext
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/10 12:32
 */
public class TravelContext {

    private TravelStrategy strategy;

    public TravelContext(TravelStrategy strategy) {
        this.strategy = strategy;
    }

    public void selectTravel() {
        this.strategy.travel();
    }

}
