package org.masteryourself.tutorial.algorithm;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : BinarySearch
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/12/8 17:29
 */
@Slf4j
public class BinarySearch {

    public static int binarySearch(int[] a, int t) {
        // 定义左边界和右边界
        int l = 0, r = a.length - 1;
        while (l <= r) {
            // 获取中间索引值, (l + r) / 2 可能会发生溢出, 所以用无符号右移运算
            int m = (l + r) >>> 1;
            // 如果正好查询到, 返回中间索引
            if (t == a[m]) {
                return m;
            }
            // 如果查找值 > 中间值, 说明需要在右边界中继续查找
            else if (t > a[m]) {
                l = m + 1;
            }
            // 如果查找值 < 中间值, 说明需要在左边界中继续查找
            else {
                r = m - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 8, 11, 19, 22, 31, 35, 40, 45, 48, 49, 50};
        int target = 50;
        int idx = binarySearch(array, target);
        log.info("查找到的索引位置是 {}", idx);
    }

}
