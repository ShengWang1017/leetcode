package com.leetcode.Hash;

import java.util.HashMap;
import java.util.HashSet;

public class LongestConsecutive {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 8, 9, 10};
        System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] nums) {
        if (nums.length==0) return 0;
        int maxLength = 1;
        HashSet<Integer> intSet = new HashSet<>();
        for (int num : nums) {
            intSet.add(num);
        }
        for (int num : nums) {
            // 应该判断x-1是否存在，如果存在，就直接不用管了，因为肯定不是最长，所以可以算下一个数了
            int currentLength = 1;
            while (intSet.contains(num - 1)) {
                num--;
            }
            while (intSet.contains(num + 1)) {
                currentLength++;
                num++;
            }
            maxLength = Math.max(currentLength, maxLength);

        }
        return maxLength;
    }
}
