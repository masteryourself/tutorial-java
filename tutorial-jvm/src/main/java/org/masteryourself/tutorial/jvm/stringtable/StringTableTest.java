package org.masteryourself.tutorial.jvm.stringtable;

/**
 * <p>description : 串池字节码分析
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/30 5:52 PM
 */
public class StringTableTest {

    /**
     * stringTable["a", "b", "ab"]
     */
    public static void main(String[] args) {
        String a = "a";
        String b = "b";
        // 存在串池中
        String c = "ab";
        // 字符串变量拼接的原理是 StringBuilder
        // new StringBuilder().append("a").append("b").toString()
        // toString() 方法会使用 new 创建一个新的 String 对象，存在堆中
        String d = a + b;
        // 字符串常量拼接的原理是编译期优化，存在串池中
        String e = "a" + "b";
        // false
        System.out.println(c == d);
        // true
        System.out.println(c == e);
    }

}
