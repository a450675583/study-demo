package com.elgin.study.demo.leetcode.array;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * ⭐️ 两个数组的交集 II
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 */
public class TwoArrayIntersection {

    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        Integer[] arr = intersection(nums1,nums2);
        Arrays.stream(arr).forEach(x -> System.out.print(x));
    }

    private static Integer[] intersection(int[] nums1,int[] nums2){
        if(nums1.length > nums2.length){
           intersection(nums2,nums1);
        }
        Map<Integer,Integer> nums1Map = Maps.newHashMap();
        List<Integer> list = Lists.newArrayList();
        Arrays.stream(nums1).forEach(x -> nums1Map.put(x,1));
        Arrays.stream(nums2).forEach(x -> {
            if(nums1Map.containsKey(x) && !list.contains(x)){
                list.add(x);
            }
        });

        Integer[] arr = new Integer[0];
        return list.toArray(arr);
    }
}
