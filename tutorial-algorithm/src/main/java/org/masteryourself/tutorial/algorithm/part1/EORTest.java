package org.masteryourself.tutorial.algorithm.part1;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : EORTest
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/2 18:49
 */
@Slf4j
public class EORTest {

    public static void main(String[] args) {
        int a = 19;
        int b = 21;
        a = a ^ b;
        b = a ^ b; // => b = a ^ b ^ b = a ^ 0 = a
        a = a ^ b; // => a = a ^ b ^ a = 0 ^ b = b
        // a = 21, b = 19
        log.info("a = {}, b = {}", a, b);
    }

}
