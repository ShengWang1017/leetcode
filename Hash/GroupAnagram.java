package com.leetcode.Hash;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 */
public class GroupAnagram {
    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> res = groupAnagram(strs);
        System.out.println(res);
    }

    /**
     * 对于每个字符串，重新排序成一个新的字符串，
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagram(String[] strs){
        HashMap<String,List<String>> strMap = new HashMap();
        ArrayList<List<String>> resLists = new ArrayList<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
            if (strMap.containsKey(key)) {
                List<String> resList = strMap.get(key);
                resList.add(str);
            }
            else {
                ArrayList<String> resList = new ArrayList<>();
                resList.add(str);
                strMap.put(key,resList);
                resLists.add(resList);
            }
        }
        return resLists;


    }
}
