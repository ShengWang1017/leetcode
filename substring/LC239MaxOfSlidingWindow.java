package com.leetcode.substring;

import java.util.TreeMap;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回 滑动窗口中的最大值 。
 */
public class LC239MaxOfSlidingWindow {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        TreeMap<Integer, Integer> sortedWindow = new TreeMap<>();
        int[] res = new int[nums.length - k+1];
        //初始化这个排序的哈希表,第一个window
        for (int i = 0; i < k-1; i++) {
            sortedWindow.put(nums[i], sortedWindow.getOrDefault(nums[i], 0) + 1);
        }
        //窗口移动，遍历nums
        for (int i = 0,j=i+k-1; j < nums.length; i++,j++) {
            sortedWindow.put(nums[j], sortedWindow.getOrDefault(nums[j], 0) + 1);
            res[i] = sortedWindow.firstKey();

        }
        return res;
    }
}
