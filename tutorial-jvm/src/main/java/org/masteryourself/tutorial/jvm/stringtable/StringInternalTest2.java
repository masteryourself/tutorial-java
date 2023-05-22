package org.masteryourself.tutorial.jvm.stringtable;

/**
 * <p>description : 串池面试题2
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/30 6:37 PM
 */
public class StringInternalTest2 {

    public static void main(String[] args) {
        String e = "ab";
        // stringTable 串池: ["a", "b"]
        // 堆: new String("a"), new String("b"), new String("ab")
        String c = new String("a") + new String("b");
        // 调用 c.intern() 方法会尝试把 "ab" 放到串池中(如果存在就不会放入, 无论如何这个方法的返回值都是串池中的对象)
        String d = c.intern();
        // false, 因为串池中已经存在了 "ab", 这里放入失败了
        System.out.println(c == "ab");
        // true, 都是串池中的对象
        System.out.println(d == "ab");
    }

}
