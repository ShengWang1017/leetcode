package com.leetcode.doublePointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC438 {

    /**
     * 438. 找到字符串中所有字母异位词
     * @param args
     */
    public static void main(String[] args) {
        String s = "abab";
        String p = "ab";
        System.out.println(findAnagrams(s,p));
    }
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (p.length()>s.length()) return res;
        HashMap<Character, Integer> visited = new HashMap<>();
        Map<Character, Integer> pMap = new HashMap<>();

        for (int i = 0; i < p.length(); i++) {
            Character sc = s.charAt(i);
            visited.put(sc, visited.getOrDefault(sc, 0) + 1);
            Character pc = p.charAt(i);
            pMap.put(pc, pMap.getOrDefault(pc, 0) + 1);
        }

        for (int i = p.length(); i < s.length(); i++) {
            if(visited.equals(pMap)) res.add(i-p.length());
            Character sc = s.charAt(i);
            Character dec = s.charAt(i - p.length());
            if (visited.get(dec) == 1) {
                visited.remove(dec);
            } else visited.put(dec, visited.get(dec) - 1);
            visited.put(sc, visited.getOrDefault(sc, 0) + 1);
        }
        if (visited.equals(pMap)) res.add(s.length() - p.length());

        return res;
    }
}
