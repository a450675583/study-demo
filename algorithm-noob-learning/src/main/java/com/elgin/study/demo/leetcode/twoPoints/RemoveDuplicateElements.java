package com.elgin.study.demo.leetcode.twoPoints;

import java.util.Arrays;

/**
 * ⭐️ 删除排序数组中的重复元素
 *
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2gy9m/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class RemoveDuplicateElements {

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int len = removeElements(nums);
        System.out.println(len);
        System.out.println(Arrays.asList(nums));
    }

    /**
     * 双指针处理
     * i表示数组当前不重复元素的索引
     * j代表遍历的位置
     * 当发现num[i] == num[j]时 i+1 就被当成一个空位 ，j往后移动，用不相等的nums[j]填充
     * @param nums
     * @return
     */
    private static int removeElements(int[] nums){
        int i = 0;
        int j = 1;
        while (j < nums.length){
            if(nums[i] == nums[j]){
                j++;
            }else{
                nums[i+1] = nums[j];
                i++;
            }
        }
        return i+1;
    }
}
