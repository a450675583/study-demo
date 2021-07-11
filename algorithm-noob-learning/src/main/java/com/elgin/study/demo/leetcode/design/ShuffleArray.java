package com.elgin.study.demo.leetcode.design;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * ⭐️ 打乱数组
 * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
 * 实现 Solution class:
 *
 * Solution(int[] nums) 使用整数数组 nums 初始化对象
 * int[] reset() 重设数组到它的初始状态并返回
 * int[] shuffle() 返回数组随机打乱后的结果
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn6gq1/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class ShuffleArray {

    private int[] nums ;
    private int len;
    private Random random;

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        ShuffleArray shuffleArray = new ShuffleArray(nums);
        int[] x = shuffleArray.shuffle();
        Arrays.stream(x).forEach(item -> System.out.println(item));

        int[] y = shuffleArray.reset();
        Arrays.stream(y).forEach(item -> System.out.println(item));
    }

    public ShuffleArray(int[] nums){
        this.nums = nums;
        this.len = nums.length;
        this.random = new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return this.nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] result = nums.clone();
        List<Integer> list = Arrays.stream(result).boxed().collect(Collectors.toList());
        Collections.shuffle(list);
        return list.stream().mapToInt(x -> x.intValue()).toArray();
    }

}
