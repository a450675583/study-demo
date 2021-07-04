package com.elgin.study.demo.leetcode.string;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * ⭐️ 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 */
public class ValidLetterChangePositionStr {

    public static void main(String[] args) {
        System.out.println(check("anagram","nagaram"));
        System.out.println(check("rat","car"));
    }

    private static boolean check(String str1,String str2){
        if(str1.length() != str2.length()){
            return false;
        }
        Map<Character, Integer> map1 = getCharacterIntegerMap(str1);

        Map<Character,Integer> map2 = getCharacterIntegerMap(str2);

        if(map1.equals(map2)){
            return true;
        }

        return false;

    }

    private static Map<Character, Integer> getCharacterIntegerMap(String str1) {
        Map<Character,Integer> map1 = Maps.newHashMap();
        int i = 0;
        while (i < str1.length()){
            char s = str1.charAt(i);
            Integer count = map1.get(s);
            count = count!=null ? count : 0;
            map1.put(s, count + 1);
            i++;
        }
        return map1;
    }
}
