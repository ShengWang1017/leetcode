package com.leetcode.ordinaryArray;

public class LC189 {

    //原地轮转
    //[1,2,3,4,5,6,7]
    /*public static int[] rotate(int[] nums, int k) {
        int temp = 0;
        int tempNext = 0;
        int l = nums.length;
        int next=0;
        if ((l % k) == 0) {
            int n = l / k;
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < n; j++) {
                    int nowP = (i + j * k) % l;
                    int nextP = (i + j * k + k) % l;
                    //当前值传给now
                    int now = nums[nowP];
                    //next的值传给数组的当前位置，
                    nums[nowP] = next;
                    //再把now的值传给next
                    next = now;
                }
            }
        } else {

        }


    }*/


}
