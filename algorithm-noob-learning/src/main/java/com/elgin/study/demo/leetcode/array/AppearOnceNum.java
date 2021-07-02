package com.elgin.study.demo.leetcode.array;

/**
 * ⭐️ 数组中只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 输入: [2,2,1]
 * 输出: 1
 *
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
public class AppearOnceNum {

    public static void main(String[] args) {
        int[] arr = {2,2,1};
        System.out.println(appearOnceNum(arr));
        //还可以使用集合，一边添加 一边remove
    }

    /**
     * 异或运算的性质：
     * a ^ 0 = a;
     * a ^ a = 0;
     * a ^ b ^ c = a ^ c ^ b  异或运算满足交换律和结合律
     * 即 ：a ^ b ^ a=b^ a^ a=b^ (a^a)=b^0=b
     * @param arr
     * @return
     */
    private static int appearOnceNum(int[] arr){
        int temp = 0;
        for(int i : arr){
            temp = temp ^ i;
        }
        return temp;
    }
}
