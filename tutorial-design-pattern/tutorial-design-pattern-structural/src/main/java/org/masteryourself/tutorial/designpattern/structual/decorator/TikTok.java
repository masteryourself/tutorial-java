package org.masteryourself.tutorial.designpattern.structual.decorator;

/**
 * <p>description : TikTok
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/27 6:05 PM
 */
public interface TikTok {

    void broadcast();

    class GirlAnchor implements TikTok {

        @Override
        public void broadcast() {
            System.out.println("美女开始直播");
        }
    }

}
