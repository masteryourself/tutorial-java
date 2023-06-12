package org.masteryourself.tutorial.designpattern.structual.adapter;

/**
 * <p>description : MoviePlayer
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/27 11:52 AM
 */
public interface MoviePlayer {

    // 播放电影并返回字幕
    String play(String movieName);

    class DefaultMoviePlayer implements MoviePlayer {

        @Override
        public String play(String movieName) {
            System.out.println(movieName + "开始播放");
            return "混蛋";
        }
    }

    class MoviePlayerAdapter implements MoviePlayer {

        private Translator translator;
        private MoviePlayer target;

        /**
         * 目标（Target）接口：可以是抽象类或接口。客户希望直接用的接口
         * 适配者（Adaptee）类：隐藏的转换接口
         * 适配器（Adapter）类：它是一个转换器，通过继承或引用适配者的对象，把适配者接口转换成目标接口
         */
        public MoviePlayerAdapter(Translator translator, MoviePlayer moviePlayer) {
            this.translator = translator;
            this.target = moviePlayer;
        }

        @Override
        public String play(String movieName) {
            String subtitle = target.play(movieName);
            return translator.translate(subtitle);
        }
    }

}
