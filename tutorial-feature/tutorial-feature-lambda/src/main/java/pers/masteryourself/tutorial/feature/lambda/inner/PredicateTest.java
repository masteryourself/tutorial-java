package pers.masteryourself.tutorial.feature.lambda.inner;

import org.junit.Test;

import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * <p>description : PredicateTet, {@link Predicate}
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/6/7 13:06
 */
public class PredicateTest {

    @Test
    public void test1() {
        Predicate<Integer> predicate = i -> i > 8;
        // true
        System.out.println(predicate.test(9));
        // false
        System.out.println(predicate.test(7));
    }

    @Test
    public void test2() {
        IntPredicate predicate = i -> i > 8;
        // true
        System.out.println(predicate.test(9));
        // false
        System.out.println(predicate.test(7));
    }

    @Test
    public void test3() {
        IntPredicate predicate1 = i -> i > 8;
        IntPredicate predicate2 = i -> i < 10;
        // 与 true
        System.out.println(predicate1.and(predicate2).test(9));
        // 或 true
        System.out.println(predicate1.or(predicate2).test(9));
        // 非 false
        System.out.println(predicate1.negate().test(9));
    }

    @Test
    public void test04() {
        // true
        System.out.println(Predicate.isEqual("Hello").test("hello"));
        // false
        System.out.println(Predicate.isEqual("Hello").test("Hello"));
    }

}
