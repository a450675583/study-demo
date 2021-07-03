package com.elgin.study.demo.leetcode.array;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * ⭐️ 加1
 *
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 *
 *
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 *
 *
 * 输入：digits = [0]
 * 输出：[1]
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2cv1c/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class ArrayPlusOne {

    public static void main(String[] args) {
        int[] arr = {4,3,2,1};
        int[] plusOne = plusOne(arr);
        Arrays.stream(plusOne).forEach(x -> System.out.print(x));
    }

    private static int[] plusOne(int[] arr){
        int temp = 0;
        int[] result = new int[arr.length + 1];
        for (int i = arr.length -1; i >= 0; i--) {
            int val = arr[i] + temp;
            if(i == arr.length -1){
                val = arr[i] + 1;
            }
            if(val >= 10){
                temp = 1;
                result[i+1] = val -10;
            }else{
                //clear temp
                temp = 0;
                result[i+1] = val;
            }
        }
        if(temp == 1){
            result[0] = 1;
            return result;
        }
        return Arrays.copyOfRange(result,1,result.length);
    }
}
