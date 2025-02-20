package com.leetcode.doublePointer;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */
public class LC42Trap {
    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(height));
    }


    public static Integer trap(int[] height) {
        int l = height.length;
        int[] leftToRight = new int[l];
        int[] rightToLeft = new int[l];
        int max =0;
        //从左到右扫过，找到每个索引对应的最大值
        for (int i = 0; i < l; i++) {
            if (height[i] > max) {
                max = height[i];
            }
            leftToRight[i] = max;
        }
        //从右往左，
        max = 0;
        for (int i = l - 1; i >= 0; i--) {
            if (height[i] > max) {
                max = height[i];
            }
            rightToLeft[i] = max;
        }
        //遍历柱子，算出每个柱子上的积水并求和
        int sum = 0;
        for (int i = 0; i < l; i++) {
            int min = Math.min(leftToRight[i], rightToLeft[i]);
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }
}
