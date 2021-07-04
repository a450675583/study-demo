package com.elgin.study.demo.leetcode.twoPoints;

/**
 * ⭐️ 实现 strStr()
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
 *
 * 说明：
 *
 * 当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，needle是空字符串时我们应当返回 0 。这与 C 语言的strstr()以及 Java 的indexOf()定义相符。
 *
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 *
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 *
 * 输入：haystack = "", needle = ""
 * 输出：0
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnr003/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class ImplIndexOf {

    public static void main(String[] args) {
//        System.out.println(strStr("hello","ll"));
//        System.out.println(strStr("aaaaa","bba"));
//        System.out.println(strStr("",""));
        System.out.println(strStr("mississippi","issip"));
    }

    public static int strStr(String haystack, String needle) {
        if(haystack == null || needle== null || needle.length()==0){
            return 0;
        }
        if(haystack.length() < needle.length()){
            return -1;
        }

        int i = 0;
        int j = 0;

        //第一次相等的位置,此时j=0
        int tmp = -1;
        while (i < haystack.length()){
            if(haystack.charAt(i) == needle.charAt(j)){
                if(j == 0){
                    tmp = i;
                }
                if(j == needle.length() - 1){
                    return tmp;
                }
                j++;
            }else{
                //中间某一个字母匹配失败，重置j,使其从下一次从头开始匹配
                j = 0;
                //重置i
                if(tmp >= 0){
                    i = tmp + 1;
                    tmp = -1;
                    continue;
                }
            }
            i++;
        }
        return -1;
    }
}
