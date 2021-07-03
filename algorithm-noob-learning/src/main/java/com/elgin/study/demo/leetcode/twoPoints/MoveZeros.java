package com.elgin.study.demo.leetcode.twoPoints;

import java.util.Arrays;

/**
 * ⭐️ 移动0
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class MoveZeros {

    public static void main(String[] args) {
        int[] arr = {0,1,0,3,12};
        moveZero1(arr);
        Arrays.stream(arr).forEach(x -> {
            System.out.println(x);
        });

        int[] arr1 = {0,1,0,3,12};
        moveZero2(arr1);
        Arrays.stream(arr1).forEach(x -> {
            System.out.println(x);
        });
    }


    /**
     * 非0往前移动，使用i记住非0元素结束位置
     * 最后i后剩余位置补0
     * @param arr
     */
    private static void moveZero1(int[] arr){
        //记录非0索引位置
        int i = 0;
        for (int j = 0; j < arr.length; j++) {
            if(arr[j] !=0 ){
                arr[i] = arr[j];
                i++;
            }
        }
        //i后面的位置都置为0
        while (i  < arr.length){
            arr[i] = 0;
            i++;
        }
    }

    /**
     * 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
     * @param arr
     */
    private static void moveZero2(int[] arr){
        int len = arr.length;
        int left = 0;
        int right =0;
        while (right < len){
            if(arr[right] != 0){
                //右指针数非0，交换左右指针的数
                swapArrayElement(arr,left,right);
                left++;
            }
            right++;
        }
    }

    private static void swapArrayElement(int[] array ,int left,int right){
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
