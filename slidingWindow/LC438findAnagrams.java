package com.leetcode.slidingWindow;

import java.io.CharArrayReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LC438findAnagrams {

    /**
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的
     * 异位词
     *  的子串，返回这些子串的起始索引。不考虑答案输出的顺序
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) {
            return new ArrayList<Integer>();
        }
        int lengthWindow = p.length();
        HashMap<Character, Integer> pHashMap = new HashMap<>();
        HashMap<Character, Integer> sHashMap = new HashMap<>();
        //建立p的哈希表
        for (int i = 0; i < lengthWindow; i++) {
            char pChar = p.charAt(i);
            pHashMap.put(pChar , pHashMap.getOrDefault(pChar, 0) + 1);
        }

        //abb
        ArrayList<Integer> resultList = new ArrayList<>();
        for (int right = 0; right < s.length(); right++) {
            //把右边界放入hashmap
            while (right >= lengthWindow) {
                if (pHashMap.equals(sHashMap)) {
                    resultList.add(right - lengthWindow);
                }
                //去除左边界对应的元素一个，如果减小到0，踢出哈希表
                char lChar = s.charAt(right - lengthWindow+1);
                sHashMap.put(lChar, sHashMap.get(lChar) - 1);
                if (sHashMap.get(lChar) == 0) {
                    sHashMap.remove(lChar);
                }
            }
            char rChar = s.charAt(right);
            sHashMap.put(rChar, sHashMap.getOrDefault(rChar, 0) + 1);

        }
        if (pHashMap.equals(sHashMap)) {
            resultList.add(s.length() - lengthWindow);
        }
        return resultList;
    }

}
