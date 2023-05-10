package org.masteryourself.tutorial.algorithm.leetcode.binarysearch;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : Exercise35
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/9 19:16
 */
@Slf4j
public class Exercise35 {

    public int searchInsert(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < nums[m]) {
                j = m - 1;
            } else if (target > nums[m]) {
                i = m + 1;
            } else {
                return m;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        log.info("{}", new Exercise35().searchInsert(new int[]{1, 3, 5, 6}, 5));
    }

}
