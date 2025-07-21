package com.leetcode.TwoPoint;

public class MoveZeros {


    public static void moveZeros(int[] nums) {

        int slow = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            else {
                int temp = nums[slow];
                nums[slow] =nums[i];
                slow++;
                if (temp == 0) {
                    nums[i] = 0;
                }
            }
        }
    }
}
