package com.leetcode.slidingWindow;

import java.util.HashSet;

public class LC3LengthOfLongestSubstring {

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 长度。
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
/*        if (s.length()==0) return 0;
        int left = 0,right = 0;
        int maxLength = 0;
        int currentLength = 0;
        HashSet<Character> existedSet = new HashSet<>();

        while (right < s.length()) {
            char r = s.charAt(right);
            if (existedSet.contains(r)) {
                char l = s.charAt(left);
                existedSet.remove(l);
                left++;
                currentLength--;
            } else {
                existedSet.add(r);
                right++;
                currentLength++;
            }
            maxLength = Math.max(currentLength, maxLength);
        }
        return maxLength;*/

        //模板

        if (s.length()==0) return 0;
        HashSet<Object> existCharSet = new HashSet<>();
        int maxLength = 0;
        for (int left = 0, right = 0; right < s.length(); right++) {
            while (existCharSet.contains(s.charAt(right))) {
                existCharSet.remove(s.charAt(left));
                left++;
            }
            existCharSet.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}
