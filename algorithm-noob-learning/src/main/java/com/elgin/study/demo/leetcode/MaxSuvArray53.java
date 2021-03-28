package com.elgin.study.demo.leetcode;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：0
 * 示例 4：
 *
 * 输入：nums = [-1]
 * 输出：-1
 * 示例 5：
 *
 * 输入：nums = [-100000]
 * 输出：-100000
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -105 <= nums[i] <= 105
 *  
 *
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 最大子序和   贪心算法  +  动态规划
 * @author zxs
 * 2021/3/27
 */
public class MaxSuvArray53 {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(maxSubArray1(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }


    public static int maxSubArray(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        int max = nums[0];
        int sum = nums[0];
        for (int i = 1;i<nums.length;i++){
            //当前元素之前的和小于0，则丢弃之前的和，只取当前的值
            sum = Math.max(sum + nums[i], nums[i]);
            max = Math.max(sum,max);
        }
        return max;
    }

    public static int maxSubArray1(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        int max = nums[0];
        int pre = nums[0];
        for (int i = 1;i<nums.length;i++){
            //当前元素之前的和小于0，则丢弃之前的和，只取当前的值
            pre = Math.max(pre + nums[i], nums[i]);
            max = Math.max(pre,max);
        }
        return max;
    }


}
