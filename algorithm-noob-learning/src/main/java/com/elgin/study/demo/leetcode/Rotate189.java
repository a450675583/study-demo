package com.elgin.study.demo.leetcode;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * //给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * //
 * //
 * //
 * // 进阶：
 * //
 * //
 * // 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * // 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 * //
 * //
 * //
 * //
 * // 示例 1:
 * //
 * //
 * //输入: nums = [1,2,3,4,5,6,7], k = 3
 * //输出: [5,6,7,1,2,3,4]
 * //解释:
 * //向右旋转 1 步: [7,1,2,3,4,5,6]
 * //向右旋转 2 步: [6,7,1,2,3,4,5]
 * //向右旋转 3 步: [5,6,7,1,2,3,4]
 * //
 * //
 * // 示例 2:
 * //
 * //
 * //输入：nums = [-1,-100,3,99], k = 2
 * //输出：[3,99,-1,-100]
 * //解释:
 * //向右旋转 1 步: [99,-1,-100,3]
 * //向右旋转 2 步: [3,99,-1,-100]
 * //
 * //
 * //
 * // 提示：
 * //
 * //
 * // 1 <= nums.length <= 2 * 104
 * // -231 <= nums[i] <= 231 - 1
 * // 0 <= k <= 105
 * //
 * //
 * //
 * //
 * // Related Topics 数组
 * // 👍 928 👎 0
 *
 * 旋转数组
 * @author zxs
 * 2021/3/29
 */
public class Rotate189 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};

        rotate(nums,3);
        Arrays.stream(nums).forEach(x -> {
            System.out.println(x);
        });



    }

    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        Integer[] newarr = new Integer[len];
        for (int i=0 ;i<len ;i++){
            int p = i+k;
            int index = p%len;
            newarr[index] = nums[index];
            nums[index] = newarr[i]!=null ? newarr[i] : nums[i];
        }
    }
}
