package org.masteryourself.tutorial.algorithm.leetcode.binarysearch;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : LeetCode704
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/9 19:11
 */
@Slf4j
public class LeetCode704 {

    public int search(int[] nums, int target) {
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
        return -1;
    }

    public static void main(String[] args) {
        log.info("{}", new LeetCode704().search(new int[]{-1, 0, 3, 5, 9, 12}, 9));
    }

}
