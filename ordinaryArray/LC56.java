package com.leetcode.ordinaryArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 */
public class LC56 {
    public static void main(String[] args) {
/*        int[][] arr = new int[][]
        int[][] res = merge();
        System.out.println(res);*/
    }

    /**
     * 通过start，end，left，right四个指针来比较区间，但是！！！必须保证区间是逐渐往无穷变大的，不能往回走。
     * 所以先把数组排下序
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0] < o2[0]) ? -1 : ((o1[0] == o2[0]) ? 0 : 1);
            }
        });

        ArrayList<int[]> res = new ArrayList<>();

        int start = intervals[0][0];
        int end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            if (left > end) {
                res.add(new int[]{start, end});
                start = left;
                end = right;
            } else if (left <= end && right>end) {
                end= right;
            }
        }
        res.add(new int[]{start, end});
        int l = res.size();
        int[][] resArray = new int[l][2];
        for (int i = 0; i < res.size(); i++) {
            resArray[i] =(int[]) res.get(i);
        }

        return resArray;
    }



}
