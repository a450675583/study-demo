package com.elgin.study.demo.sort;

import java.util.Arrays;

/**
 * 冒泡排序实现
 */
public class  BubbleSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) {

        int[] arr = Arrays.copyOf(sourceArray,sourceArray.length);

        for(int i=1; i< arr.length; i++){

            boolean sortFlag = true;
            for(int j=0; j< arr.length - i; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    sortFlag = false;
                }
            }
            if(sortFlag){
                break;
            }
            printArray(arr);
        }

        return arr;
    }


    public static void main(String[] args) {
        int[] arr = {8,6,1,12,9,7,1};
        IArraySort arraySort = new  BubbleSort();
        arraySort.sort(arr);
    }
}
