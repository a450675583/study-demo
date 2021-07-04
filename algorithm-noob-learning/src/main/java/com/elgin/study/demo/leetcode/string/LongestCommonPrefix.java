package com.elgin.study.demo.leetcode.string;

import java.util.Arrays;
import java.util.Comparator;

/**
 * ⭐️ 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""
 *
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] arr = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(arr));

        String[] arr1 = {"dog","racecar","car"};
        System.out.println(longestCommonPrefix(arr1));
    }

    private static String longestCommonPrefix(String[] arr){
        if(arr.length == 1){
            return arr[0];
        }
        Arrays.sort(arr, Comparator.comparingInt(x -> x.length()));
        String shortest = arr[0];
        arr = Arrays.copyOfRange(arr,1,arr.length);
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < shortest.length()){
            for (int j = 0; j < arr.length; j++) {
                char c = shortest.charAt(i);
                String s = arr[j];
                if(i == shortest.length()){
                    return result.toString();
                }
                if(c != s.charAt(i)){
                    return result.toString();
                }
                if(j == arr.length - 1){
                    result.append(c);
                }
            }
            i++;
        }
        return result.toString();
    }
}
