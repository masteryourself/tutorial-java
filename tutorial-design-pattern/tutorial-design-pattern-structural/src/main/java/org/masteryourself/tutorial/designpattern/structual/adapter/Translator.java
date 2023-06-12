package org.masteryourself.tutorial.designpattern.structual.adapter;

/**
 * <p>description : Translator
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/27 11:52 AM
 */
public interface Translator {

    String translate(String subtitle);

    class ZH2JapanTranslator implements Translator {

        @Override
        public String translate(String subtitle) {
            return "原字幕" + "[" + subtitle + "]" +
                    "翻译后字幕" + "[" + "八嘎" + "]";
        }
    }

}
