package com.elgin.study.demo.leetcode.array;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.stream.Stream;

/**
 * ⭐️ 数组是否存在重复元素
 * 给定一个整数数组，判断是否存在重复元素。
 *
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 *
 * 输入: [1,2,3,1]
 * 输出: true
 *
 * 输入: [1,2,3,4]
 * 输出: false
 *
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 */
public class ArrayExistRepeatElement {

    public static void main(String[] args) {
        int[] array = {1,1,1,3,3,4,3,2,4,2};
        int[] array1 = {1,2,3,4};
        System.out.println(existRepeatElements(array1));
    }

    private static boolean existRepeatElements(int[] array){
        Map<Integer,Integer> map = Maps.newHashMap();
        for (int i : array) {
            Integer val = map.get(i);
            if(val !=null){
                return true;
            }
            map.put(i,1);
        }
        return false;
    }
}
