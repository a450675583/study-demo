package com.elgin.study.demo.leetcode.dp;

/**
 * ⭐️ 爬楼梯
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 *
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class ClimbStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs(10));
    }

    private static int climbStairs(int n){
        if(n <= 2){
            return n;
        }
        int first = 1;
        int second = 2;
        int sum = 0;
        while (n > 2){
            sum = first + second;
            first  = second;
            second = sum;
            n--;
        }
        return sum;
    }
}
