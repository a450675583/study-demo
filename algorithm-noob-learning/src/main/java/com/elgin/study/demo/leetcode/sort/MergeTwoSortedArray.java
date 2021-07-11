package com.elgin.study.demo.leetcode.sort;

import java.util.Arrays;

/**
 * ⭐️ 合并两个有序数组
 *
 * 给你两个有序整数数组nums1 和 nums2，请你将 nums2 合并到nums1中，使 nums1 成为一个有序数组。
 *
 * 初始nums1 和 nums2 的元素数量分别m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnumcr/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class MergeTwoSortedArray {

    public static void main(String[] args) {
        int[] num1 = new int[]{1,2,3,0,0,0};
        int[] num2 = new int[]{2,5,6};
        merge(num1,3,num2,3);
        Arrays.stream(num1).forEach(x -> System.out.println(x));
    }

    private static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int num[] = new int[m+n];
        int index = 0;
        while (i < m && j < n){
            if(nums1[i] > nums2[j]){
                num[index] = nums2[j];
                j++;
            }else if(nums1[i] <= nums2[j]){
                num[index] = nums1[i];
                i++;
            }
            index++;
        }
        while (i < m){
            num[index++] = nums1[i++];
        }

        while (j < n){
            num[index++] = nums2[j++];
        }
        System.arraycopy(num,0,nums1,0,m+n);
    }
}
