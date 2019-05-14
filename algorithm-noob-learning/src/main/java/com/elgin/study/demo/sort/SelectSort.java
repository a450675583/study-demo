package com.elgin.study.demo.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
 *
 * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 *
 * 重复第二步，直到所有元素均排序完毕。
 */
public class SelectSort implements IArraySort {


    @Override
    public int[] sort(int[] sourceArray) {

        int[] arr = Arrays.copyOf(sourceArray,sourceArray.length);

        //总共n-1次比较
        for(int i=0; i< arr.length -1 ;i++){

            int min = i;

            //每轮需要比较次数n-i
            for(int j=i + 1; j< arr.length;j++){
                if(arr[j] < arr[min]){
                    //记录最小元素下标
                    min = j;
                }
            }

            //找到的最小值跟i位所在值交换
            if(i != min){
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }

            printArray(arr);

        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] arr = {8,6,1,12,9,7,1};
        IArraySort arraySort = new SelectSort();
        arraySort.sort(arr);
    }

}
