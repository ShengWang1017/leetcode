package com.leetcode.ordinaryArray;


import java.util.ArrayList;
import java.util.TreeSet;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组
 * 是数组中的一个连续部分。
 */
public class LC53MaxSubArray {
    public static void main(String[] args) {
        int[] nums = {-2,-1};
        int res = maxSubarray(nums);
        System.out.println(res);

    }

    public static int maxSubarray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int minPre = 0;
        int pre = 0;
        for (int num : nums) {
            pre+=num;
            maxSum = Math.max(maxSum, pre - minPre);
            minPre = Math.min(minPre, pre);
        }
        return maxSum;

    }



}
