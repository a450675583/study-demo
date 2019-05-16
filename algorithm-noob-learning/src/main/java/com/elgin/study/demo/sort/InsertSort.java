package com.elgin.study.demo.sort;

import java.util.Arrays;

/**
 * 插入排序
 *
 * 将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
 *
 * 从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。（如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）
 *
 */
public class InsertSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) {

        int[] arr = Arrays.copyOf(sourceArray,sourceArray.length);

        //遍历未排序序列，从小标1的元素开始，下表0的元素默认有序
        for(int i=1;i< arr.length;i++){

            //记录需要插入的值
            int temp = arr[i];

            //从已经排序序列最右边开始由右边向左比较
            int j = i;
            while (j>0 && temp < arr[j-1]){
                arr[j] = arr[j-1];
                j--;
            }

            //存在比其小的数，插入
            if(j != i){
                arr[j] = temp;
            }

        }

        return arr;
    }


    public static void main(String[] args) {
        int[] arr = {8,6,1,12,9,7,1};
        IArraySort arraySort = new InsertSort();
        int[] ss=arraySort.sort(arr);
        arraySort.printArray(ss);
    }
}
