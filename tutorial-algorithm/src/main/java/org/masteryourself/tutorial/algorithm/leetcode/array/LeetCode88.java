package org.masteryourself.tutorial.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * <p>description : LeetCode88
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/16 11:35
 */
public class LeetCode88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[nums1.length];
        int p1 = 0, p2 = 0;
        for (int i = 0; i < result.length; i++) {
            // 1. 如果 p1 超出边界, 直接使用 p2 赋值
            if (p1 == m) {
                result[i] = nums2[p2];
                p2++;
            }
            // 2. 如果 p2 超出边界, 直接使用 p1 赋值
            else if (p2 == n) {
                result[i] = nums1[p1];
                p1++;
            }
            // 3. 小数在前
            else if (nums1[p1] < nums2[p2]) {
                result[i] = nums1[p1];
                p1++;
            } else {
                result[i] = nums2[p2];
                p2++;
            }
        }
        System.arraycopy(result, 0, nums1, 0, result.length);
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        System.out.println(Arrays.toString(nums1));
        new LeetCode88().merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }

}
