package com.elgin.study.demo.leetcode.twoPoints;

import java.util.Locale;

/**
 * ⭐️ 验证回文串
 *
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 *
 * 输入: "race a car"
 * 输出: false
 */
public class CheckPalindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
    }

    private static boolean isPalindrome(String str){
        if(str!=null && str.length() == 0){
            return true;
        }
        str = str.replaceAll("[^0-9|a-z|A-Z]","").toLowerCase();
        int len = str.length();
        int i = 0;
        int j = len-1;
        while (i < j){
            if(!(str.charAt(i++) == str.charAt(j--))){
                return false;
            }
        }
        return true;
    }

}
