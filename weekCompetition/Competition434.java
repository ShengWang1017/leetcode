package com.leetcode.weekCompetition;

import java.util.*;

public class Competition434 {
    public static void main(String[] args) {
        //test1
        int[] nums = {1, 2, 2};
        int result = depart(nums);
        System.out.println(result);
    }

    public static int depart(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum+=num;
        }
        sum = Math.abs(sum);
        if (sum % 2 == 1||nums.length <= 1) {
            return 0;
        } else{
            return nums.length-1;
        }
    }

    //2.
/*    public static int[] countMentions(int numberOfUsers, List<List<String>> events) {

    }*/

    //3.
/*
    public static int maxFrequency(int[] nums, int k) {
        ArrayList<Integer> numCount = new ArrayList<>(50);
        for (int num : nums) {
            numCount.set(num,numCount.get(num)+1);
        }
        Integer maxCount = Collections.max(numCount);

    }
*/

}
