package com.elgin.study.demo.leetcode.twoPoints;

import java.util.Arrays;

/**
 * ⭐️ 反转字符串
 *
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 *
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 *
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnhbqj/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class ReverseString {

    public static void main(String[] args) {
        char[] arr ={'h','e','l','l','o'};
        reverseString(arr);
        System.out.println(new String(arr));

        char[] arr1 ={'H','a','n','n','a','h'};
        reverseString(arr1);
        System.out.println(new String(arr1));
    }

    private static void reverseString(char[] arr){
        int i = 0;
        int j = arr.length - 1;
        while (i < j){
            swapArrayElement(arr,i,j);
            i++;
            j--;
        }
    }

    private static void swapArrayElement(char[] array ,int left,int right){
        char temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
