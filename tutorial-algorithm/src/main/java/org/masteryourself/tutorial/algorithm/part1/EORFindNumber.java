package org.masteryourself.tutorial.algorithm.part1;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : EORFindNumber
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/3 11:55
 */
@Slf4j
public class EORFindNumber {

    private static void eorFindNumber(int[] array) {
        int eor = 0;
        for (int i : array) {
            eor = eor ^ i;
        }
        log.info("出现了奇数次的数字是 {}", eor);
    }

    public static void main(String[] args) {
        // 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
        int[] array = {5, 2, 5, 1, 3, 3, 2};
        eorFindNumber(array);
    }

}
