package com.leetcode.doublePointer;

/**
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 *
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 */
public class LC11HoldTheMostWater {
    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int maxArea = maxArea(height);
        System.out.println(maxArea);
    }

    /**
     * 关键！！！：这个较短的指针对应的数不会作为容器的边界了
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length-1;
        int max = (right - left) * Math.min(height[left], height[right]);
        while (right != left) {
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
            int area = (right - left) * Math.min(height[left], height[right]);
            if (area>max) max = area;
        }
        return max;
    }


}
