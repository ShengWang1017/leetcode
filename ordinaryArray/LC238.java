package com.leetcode.ordinaryArray;

import java.util.Arrays;

public class LC238 {

    public static int[] productExceptSelf(int[] nums) {
        int l =nums.length;
        int[] res = new int[l];
        Arrays.fill(res, 1);
        int left = 1;
        int right = 1;
        for (int i = 0; i < l; i++) {
            res[i] = left*res[i];
            res[l - i-1] = right*res[l-i-1];
            left *= nums[i];
            right *= nums[l - i-1];
        }
        return res;



    }
}
