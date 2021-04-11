package com.elgin.study.demo.leetcode;

/**
 * //给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * //
 * // 说明：本题中，我们将空字符串定义为有效的回文串。
 * //
 * // 示例 1:
 * //
 * // 输入: "A man, a plan, a canal: Panama"
 * //输出: true
 * //
 * //
 * // 示例 2:
 * //
 * // 输入: "race a car"
 * //输出: false
 * //
 * // Related Topics 双指针 字符串
 * // 👍 358 👎 0
 * @author zxs
 * 2021/4/4
 */
public class IsPalindrome125 {

    public static void main(String[] args) {
        System.out.println(isPalindrome("0p"));
    }


    public static boolean isPalindrome(String s) {
        if(s == null || s.length() == 0){
            return true;
        }
        int i= 0;
        int j= s.length() - 1;
        while (i<j){
            char ci = s.charAt(i);
            if(!Character.isLetterOrDigit(ci)){
                i++;
                continue;
            }
            char cj = s.charAt(j);
            if(!Character.isLetterOrDigit(cj)){
                j--;
                continue;
            }

            if(!String.valueOf(ci).equalsIgnoreCase(String.valueOf(cj))){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
