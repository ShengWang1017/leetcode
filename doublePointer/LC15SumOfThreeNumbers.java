package com.leetcode.doublePointer;

import java.util.*;

/**
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 */
public class LC15SumOfThreeNumbers {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println(lists);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        //检测输入的数组大小
        if (nums == null || nums.length < 3) {
            return Collections.emptyList(); // 如果数组为空或长度小于3，直接返回空列表
        }
        Arrays.sort(nums);
        int p1 = 0;
        HashSet<List<Integer>> listHashSet = new HashSet<>();
        while (p1 <= nums.length - 3) {
            if (nums[p1] + nums[p1 + 1] + nums[p1 + 2] > 0) break; // 优化一
            if (nums[p1] + nums[nums.length - 2] + nums[nums.length - 1] < 0){p1++; continue; }// 优化二
            int p2 = p1+1;
            int p3 = nums.length-1;
            while (p2 < p3) {
                if (nums[p1] + nums[p2] + nums[p3] < 0) {
                    p2++;
                } else if (nums[p1] + nums[p2] + nums[p3] > 0) {
                    p3--;
                } else {
                    listHashSet.add(Arrays.asList(nums[p1], nums[p2], nums[p3]));
                    p3--;
                    p2++;
                }
            }
            //p1后移一位
            p1++;
        }
        List<List<Integer>> lists = listHashSet.stream().toList();
        return lists;
    }

}
