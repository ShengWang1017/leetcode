package com.leetcode.ordinaryArray;

/**
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 *
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 */
public class LC41 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 0};
        int res = firstMissingPositive(nums);
        System.out.println(res);
    }

    public static int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                nums[i] = 0;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            int abs = Math.abs(nums[i]);
            if (abs > 0 && abs <= nums.length) {
                if (nums[abs - 1] == 0) {
                    nums[abs - 1] = -(abs);
                } else if (nums[abs - 1] > 0) {
                    nums[abs-1] = -nums[abs-1];
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                return i+1;
            }
        }

        return nums.length+1;


    }
}
