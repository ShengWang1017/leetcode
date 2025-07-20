package Hash;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 */
public class GroupAnagram {
    public static void main(String[] args) {
        
    }
    

    public static List<List<String>> groupAnagram(String[] strs){
        Set strsSet = new HashSet();

        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            strsMap.putIfAbsent(new String(charArray), strsMap);
        }
    }
}
