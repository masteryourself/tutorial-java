package org.masteryourself.tutorial.jvm.bytecode;

/**
 * <p>description : 构造方法字节码分析
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/4 1:53 PM
 */
public class ConstructAnalysis {

    private String a = "s1";

    {
        b = 20;
    }

    private int b = 10;

    {
        a = "s2";
    }

    public ConstructAnalysis(String a, int b) {
        this.a = a;
        this.b = b;
    }

    public static void main(String[] args) {
        ConstructAnalysis d = new ConstructAnalysis("s3", 30);
        // s3
        System.out.println(d.a);
        // 30
        System.out.println(d.b);
    }

}
