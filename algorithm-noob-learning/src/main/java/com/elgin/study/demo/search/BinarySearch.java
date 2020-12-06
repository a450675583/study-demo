package com.elgin.study.demo.search;

/**
 * @author zxs
 * 2020/12/6
 */
public class BinarySearch {


    /**
     * 二分查找
     * 输入是一个有序的元素列表（必须是有序的），如果查找的元素包含在列表中，二分查找返回其位置.
     * @param arr
     * @param target
     * @return
     */
    public static int search(int[] arr , int target) {
        int low = 0;
        int high = arr.length -1;
        while (low <= high){
            int mid =(high + low)/2 ;
            int tmp = arr[mid];
            if(tmp == target){
                return mid;
            }
            if(tmp < target){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        throw new RuntimeException("not found");
    }

    public static void main(String[] args) {
        int[] arr = {1,3,6,8,13,15,17,19,23,45};
        System.out.println(search(arr,13));
    }
}
