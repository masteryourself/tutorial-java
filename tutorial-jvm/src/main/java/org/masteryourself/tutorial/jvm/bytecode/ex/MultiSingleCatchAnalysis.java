package org.masteryourself.tutorial.jvm.bytecode.ex;

/**
 * <p>description : 多个单一 catch 字节码分析
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/4 2:32 PM
 */
public class MultiSingleCatchAnalysis {

    public static void main(String[] args) {
        int i = 0;
        try {
            i = 10;
        } catch (ArithmeticException e) {
            i = 30;
        } catch (NullPointerException e) {
            i = 40;
        } catch (Exception e) {
            i = 50;
        }
    }

}
