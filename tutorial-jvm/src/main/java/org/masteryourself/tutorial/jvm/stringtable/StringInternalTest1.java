package org.masteryourself.tutorial.jvm.stringtable;

/**
 * <p>description : 串池面试题1
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/30 6:18 PM
 */
public class StringInternalTest1 {

    /**
     * new String("a") 调用的构造方法是 String(String original)
     * 所以这里有两个步骤: 首先从串池中查找 "a", 查找不到再创建后放入串池中, 然后在堆上 new String("a")
     * </br>
     * new String("a") + new String("b") 底层用到 StringBuilder.toString() 方法构造 String 对象, 这里调用的构造方法是 String(value, 0, count)
     * 注意这里传入的 value 是 char 数组, 不是字面量, 所以是直接分配到堆上
     */
    public static void main(String[] args) {
        // stringTable 串池: ["a", "b"]
        // 堆: new String("a"), new String("b"), new String("ab")
        String c = new String("a") + new String("b");
        // 调用 c.intern() 方法会尝试把 "ab" 放到串池中(如果存在就不会放入, 无论如何这个方法的返回值都是串池中的对象)
        String d = c.intern();
        // true, 因为串池中没有 "ab", 这里放入成功了
        System.out.println(c == "ab");
        // true, 都是串池中的对象
        System.out.println(d == "ab");
    }

}
