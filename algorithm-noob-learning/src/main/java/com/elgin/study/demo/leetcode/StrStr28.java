package com.elgin.study.demo.leetcode;

/**
 * //实现 strStr() 函数。
 * //
 * // 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如
 * //果不存在，则返回 -1。
 * //
 * // 示例 1:
 * //
 * // 输入: haystack = "hello", needle = "ll"
 * //输出: 2
 * //
 * //
 * // 示例 2:
 * //
 * // 输入: haystack = "aaaaa", needle = "bba"
 * //输出: -1
 * //
 * //
 * // 说明:
 * //
 * // 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * //
 * // 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 * // Related Topics 双指针 字符串
 * // 👍 769 👎 0
 * @author zxs
 * 2021/4/5
 */
public class StrStr28 {

    public static void main(String[] args) {
        System.out.println(strStr("mississippi","issipi"));
    }

    public static int strStr(String haystack, String needle) {
        if(haystack.equals(needle)){
            return 0;
        }
        if(needle.length() == 0){
            return 0;
        }
        if(haystack.length() < needle.length()){
            return -1;
        }
        int i = 0;
        int j = 0;
        int index = -1;
        int len = haystack.length();
        while (j < len){
            //第一位
            if(haystack.charAt(j) != needle.charAt(i)){
                //j往后移动还是复原
                if(index >=0){
                    j = index + 1;
                    index = -1;
                }else{
                    j++;
                }
                //i复位
                i=0;
            }else{
                //相等情况
                index = index != -1 ? index : j;
                //j往后移动
                j++;
                //i往后移动
                i++;
                if(i == needle.length()){
                    return index;
                }
            }
        }
        return -1;
    }
}
