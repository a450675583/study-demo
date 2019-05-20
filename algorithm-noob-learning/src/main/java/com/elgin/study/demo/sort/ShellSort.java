package com.elgin.study.demo.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * （1）希尔排序（shell sort）这个排序方法又称为缩小增量排序，是1959年D·L·Shell提出来的。该方法的基本思想是：
 *  设待排序元素序列有n个元素，首先取一个整数increment（小于n）作为间隔将全部元素分为increment个子序列，所有距离为increment的元素放在同一个子序列中，
 *  在每一个子序列中分别实行直接插入排序。然后缩小间隔increment，重复上述子序列划分和排序工作。直到最后取increment=1，将所有元素放在同一个子序列中排序为止。
 * （2）由于开始时，increment的取值较大，每个子序列中的元素较少，排序速度较快，到排序后期increment取值逐渐变小，子序列中元素个数逐渐增多，
 *  但由于前面工作的基础，大多数元素已经基本有序，所以排序速度仍然很快。
 *
 */
public class ShellSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) {

        int[] arr = Arrays.copyOf(sourceArray,sourceArray.length);

        methodA(arr);
        printArray(arr);

        System.out.println(" ");
        System.out.println("=======================");

        int[] arr1 = Arrays.copyOf(sourceArray,sourceArray.length);
        methodB(arr1);
        printArray(arr1);

        return arr;
    }


    private int[] methodA(int[] arr){
        /**选择increment间隔**/
        int gap=1;
        while (gap < arr.length){
            gap = gap*3+1;
        }

        while (gap > 0){
            for(int i=gap;i < arr.length ; i++){
                int temp = arr[i];
                int j = i - gap;
                while (j>=0 && arr[j] > temp){
                    arr[j+gap] = arr[j];
                    j-=gap;
                }
                arr[j+gap] = temp;
            }
            printArray(arr);
            gap = Math.floorDiv(gap,3);
        }
        return arr;
    }

    private void methodB(int[] arr){
        for(int gap=arr.length/2;gap>0;gap=gap/2){
            for(int i=gap;i<arr.length;i++){
                int j = i;
                while (j-gap>=0 && arr[j] < arr[j-gap]){
                    int temp = arr[j];
                    arr[j] = arr[j-gap];
                    arr[j-gap] = temp;
                    j-=gap;
                }
            }
            printArray(arr);
        }
    }


    public static void main(String[] args) {
        int[] arr = {8,6,1,12,9,7,13,19,11,3,14};
        IArraySort arraySort = new ShellSort();
        arraySort.sort(arr);
    }
}
