package com.elgin.study.demo.leetcode;

import java.util.Arrays;

/**
 * @author zxs
 * 2021/3/31
 */
public class MoveZeroes283 {

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};
        moveZeroes(nums);
        Arrays.stream(nums).forEach(x -> {
            System.out.println(x);
        });
    }

    public static void moveZeroes(int[] nums) {
        if(nums.length == 0){
            return;
        }
        int j = 0;
        for(int i=0 ; i<nums.length ;i++){
            if(nums[i]!=0 && i!=j){
                swap(nums,i,j);
                j++;
            }
        }
    }

    private static void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
