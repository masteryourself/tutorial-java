package pers.masteryourself.tutorial.jvm.oom;

/**
 * <p>description : StackOverFlowErrorDemo
 * <p>递归调用导致栈溢出
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/5/3 14:37
 */
public class StackOverFlowErrorDemo {

    /**
     * Exception in thread "main" java.lang.StackOverflowError
     *
     * @param args
     */
    public static void main(String[] args) {
        stackOverFlowError();
    }

    private static void stackOverFlowError() {
        stackOverFlowError();
    }

}
