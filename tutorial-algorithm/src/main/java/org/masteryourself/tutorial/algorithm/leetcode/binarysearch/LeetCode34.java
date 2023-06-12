package org.masteryourself.tutorial.algorithm.leetcode.binarysearch;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description : LeetCode34
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/10 19:14
 */
@Slf4j
public class LeetCode34 {

    public int[] searchRange(int[] nums, int target) {
        // 查找最左元素
        int leftIndex = searchLeft(nums, target);
        // 查找最右元素
        int rightIndex = searchRight(nums, target);
        return new int[]{leftIndex, rightIndex};
    }

    private int searchLeft(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        int candidate = -1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < nums[m]) {
                j = m - 1;
            } else if (target > nums[m]) {
                i = m + 1;
            } else {
                candidate = m;
                j = m - 1;
            }
        }
        return candidate;
    }

    private int searchRight(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        int candidate = -1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < nums[m]) {
                j = m - 1;
            } else if (target > nums[m]) {
                i = m + 1;
            } else {
                candidate = m;
                i = m + 1;
            }
        }
        return candidate;
    }

    public static void main(String[] args) {
        log.info("{}", new LeetCode34().searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8));
    }

}
