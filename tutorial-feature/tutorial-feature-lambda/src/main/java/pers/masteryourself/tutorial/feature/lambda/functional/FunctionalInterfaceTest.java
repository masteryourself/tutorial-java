package pers.masteryourself.tutorial.feature.lambda.functional;

import org.junit.Test;

/**
 * <p>description : FunctionalInterfaceTest
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/6/7 12:59
 */
public class FunctionalInterfaceTest {

    @Test
    public void test1() {
        FunctionalInterfaceDemo functionalInterfaceDemo = new FunctionalInterfaceDemo() {
            @Override
            public void method() {
                System.out.println("匿名内部类");
            }
        };
        functionalInterfaceDemo.method();
    }

    @Test
    public void test2() {
        FunctionalInterfaceDemo functionalInterfaceDemo = () -> System.out.println("lambda 表达式");
        functionalInterfaceDemo.method();
    }

}
