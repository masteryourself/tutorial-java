package org.masteryourself.tutorial.designpattern.structual.adapter;

/**
 * <p>description : AdapterTest
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/27 12:03 PM
 */
public class AdapterTest {

    public static void main(String[] args) {
        MoviePlayer moviePlayer = new MoviePlayer.DefaultMoviePlayer();
        String defaultSubtitle = moviePlayer.play("泰坦尼克号");
        System.out.println(defaultSubtitle);

        Translator translator = new Translator.ZH2JapanTranslator();
        MoviePlayer moviePlayerAdapter = new MoviePlayer.MoviePlayerAdapter(translator, moviePlayer);
        String japanSubtitle = moviePlayerAdapter.play("泰坦尼克号");
        System.out.println(japanSubtitle);
    }

}
