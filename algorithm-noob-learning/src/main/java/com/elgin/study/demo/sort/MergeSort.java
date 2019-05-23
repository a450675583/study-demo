package com.elgin.study.demo.sort;

import java.util.Arrays;

/**
 * 归并排序：
 * 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列；
 *
 * 设定两个指针，最初位置分别为两个已经排序序列的起始位置；
 *
 * 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置；
 *
 * 重复步骤 3 直到某一指针达到序列尾；
 *
 * 将另一序列剩下的所有元素直接复制到合并序列尾。
 *
 */
public class MergeSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) {
        if(sourceArray.length <2){
            return  sourceArray;
        }

        int[] arr = Arrays.copyOf(sourceArray,sourceArray.length);
        int mid = Math.floorDiv(arr.length,2);
        int[] left = Arrays.copyOfRange(arr,0,mid);
        int[] right = Arrays.copyOfRange(arr,mid,arr.length);

        return merge(sort(left),sort(right));
    }

    private int[] merge(int[] left,int[] right){
        int[] result = new int[left.length+right.length];

        int i=0;
        while (left.length>0 && right.length>0){
            if(left[0] <= right[0]){
                result[i++] = left[0];
                left = Arrays.copyOfRange(left,1,left.length);
            }else{
                result[i++] = right[0];
                right = Arrays.copyOfRange(right,1,right.length);
            }
        }

        while (left.length>0){
            result[i++] = left[0];
            left = Arrays.copyOfRange(left,1,left.length);
        }

        while (right.length>0){
            result[i++] = right[0];
            right = Arrays.copyOfRange(right,1,right.length);
        }

        printArray(result);

        return  result;
    }

    public static void main(String[] args) {
        int[] arr = {8,6,1,12,9,7,1};
        IArraySort arraySort = new MergeSort();
        arraySort.sort(arr);
    }
}
