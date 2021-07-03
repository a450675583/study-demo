package com.elgin.study.demo.leetcode.string;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * ⭐️ 字符串中的第一个唯一字符
 *
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * s = "leetcode"
 * 返回 0
 *
 * s = "loveleetcode"
 * 返回 2
 */
public class FirstUniqueChar {

    public static void main(String[] args) {
        System.out.println(firstUniqueChar("leetcode"));
        System.out.println(firstUniqueChar("loveleetcode"));
    }

    private static int firstUniqueChar(String str){
        Map<Character,Integer> map = Maps.newHashMap();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            Integer count = map.get(c);
            if(count == null){
                count = 1;
            }else {
                count = count + 1;
            }
            map.put(c,count);
        }

        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            Integer count = map.get(c);
            if(count == 1){
                return i;
            }
        }

        return -1;
    }
}
