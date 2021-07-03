package com.elgin.study.demo.leetcode.array;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

/**
 * ⭐️ 两数之和
 *
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2jrse/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] arr = {2,7,11,15};
        int[] result = twoSum(arr, 9);
        Arrays.stream(result).forEach(x -> System.out.println(x));

        int[] arr1 = {3,3};
        int[] result1 = twoSum(arr1, 6);
        Arrays.stream(result1).forEach(x -> System.out.println(x));
    }

    private static int[] twoSum(int[] arr, int target){
        Map<Integer,Integer> map = Maps.newHashMap();
        for (int i = 0; i < arr.length; i++) {
            int searchVal = target - arr[i];
            Integer index = map.get(searchVal);
            if(index!=null){
                return new int[]{index,i};
            }
            map.put(arr[i] , i);
        }
        return null;
    }
}
