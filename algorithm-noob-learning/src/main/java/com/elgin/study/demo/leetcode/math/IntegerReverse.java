package com.elgin.study.demo.leetcode.math;

/**
 * ⭐️ 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围[−2^31, 2^31− 1] ，就返回 0。
 *
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnx13t/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class IntegerReverse {

    public static void main(String[] args) {
        int num = 23456;
        System.out.println(reverse(num));
    }

    /**
     * 1、每次mod 10 得到当前数字最后一位的值
     * 2、result * 10 + modVal 当前反转后的值
     * 3、num/10 得到下一位的数字
     * @param num
     * @return
     */
    private static int reverse(int num){
        long result = 0;
        while (num != 0){
            int modVal = num % 10;
            result = result * 10 + modVal;
            num = num/10;
        }
        if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE){
            return 0;
        }
        return (int) result;
    }
}
