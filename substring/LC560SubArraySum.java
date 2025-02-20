package com.leetcode.substring;

import java.util.HashMap;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 */
public class LC560SubArraySum {
    public static void main(String[] args) {
        int[] nums = new int[]{1};
        int res = subArraySum2(nums, 0);
        System.out.println(res);
    }

    /**
     * 方法1，暴力求解
     * @param nums
     * @param k
     * @return
     */
    public static int subArraySum1(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum+=nums[j];
                if (sum == k) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 哈希表优化，利用“前缀和prefix”
     * @param nums
     * @param k
     * @return
     */
    public static int subArraySum2(int[] nums, int k) {
        int prefix = 0;
        int res = 0;
        HashMap<Integer, Integer> preMap = new HashMap<>();
        preMap.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            int preI = prefix-k;
            res += preMap.getOrDefault(preI, 0);
            preMap.put(prefix, preMap.getOrDefault(prefix, 0) + 1);
        }
        return res;

    }

}
