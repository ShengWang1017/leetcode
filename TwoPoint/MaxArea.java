package com.leetcode.TwoPoint;

public class MaxArea {

    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length-1;
        int maxArea = 0;
        while (left < right) {
            int currentArea = 0;
            if (height[left] <= height[right]) {
                currentArea = height[left] * (right - left);
                left++;
            } else {
                currentArea = height[right]*(right-left);
                right--;
            }
            maxArea = Math.max(currentArea, maxArea);
        }
        return maxArea;
    }
}
