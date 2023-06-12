package org.masteryourself.tutorial.algorithm.recursion;

/**
 * <p>description : E02ReversePrintString
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/13 13:26
 */
public class E02ReversePrintString {

    public static void main(String[] args) {
        print("abcd", 0);
    }

    private static void print(String str, int index) {
        if (index == str.length()) {
            return;
        }
        print(str, index + 1);
        System.out.println(str.charAt(index));
    }

}
