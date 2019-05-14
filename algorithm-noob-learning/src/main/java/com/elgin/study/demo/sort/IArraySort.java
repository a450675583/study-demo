package com.elgin.study.demo.sort;

/**
 * 排序接口
 */
public interface IArraySort {

    /**
     * 排序
     * 由低到高
     */
    int[] sort(int[] sourceArray);

    default void printArray(int[] arr){
        for(int i=0;i<arr.length;i++){
            if(i==0){
                System.out.print("[");
            }

            if(i==arr.length-1){
                System.out.print(arr[i]);
                System.out.print("]");
                continue;
            }

            System.out.print(arr[i]+ ",");
        }
    }
}
