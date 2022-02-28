package org.masteryourself.tutorial.designpattern.structual.decorator;

/**
 * <p>description : TikTokDecorator
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/27 6:07 PM
 */
public interface TikTokDecorator extends TikTok {

    void enableBeauty();

    class BeautyTikTokDecorator implements TikTokDecorator {

        private TikTok tikTok;

        public BeautyTikTokDecorator(TikTok tikTok) {
            this.tikTok = tikTok;
        }

        @Override
        public void broadcast() {
            enableBeauty();
            this.tikTok.broadcast();
        }

        @Override
        public void enableBeauty() {
            System.out.println("开启美颜功能");
        }
    }

}
