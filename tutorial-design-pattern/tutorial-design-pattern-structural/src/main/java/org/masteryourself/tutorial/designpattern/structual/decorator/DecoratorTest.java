package org.masteryourself.tutorial.designpattern.structual.decorator;

/**
 * <p>description : DecoratorTest
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/27 6:06 PM
 */
public class DecoratorTest {

    public static void main(String[] args) {
        new TikTok.GirlAnchor().broadcast();
        // 装饰
        new TikTokDecorator.BeautyTikTokDecorator(new TikTok.GirlAnchor()).broadcast();
    }

}
