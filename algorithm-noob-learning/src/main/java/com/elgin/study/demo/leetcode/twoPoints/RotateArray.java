package com.elgin.study.demo.leetcode.twoPoints;

import java.util.Arrays;

/**
 * ⭐️ 旋转数组
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 进阶：
 *
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 *
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 *
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2skh7/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class RotateArray {

    public static void main(String[] args) {
        int[] array1 = {1,2,3,4,5,6,7};
        rotateArray1(array1,3);
        Arrays.stream(array1).forEach(x -> System.out.print(x));

        System.out.println("");

        int[] array2 = {1,2,3,4,5,6,7};
        rotateArray2(array2,3);
        Arrays.stream(array2).forEach(x -> System.out.print(x));
    }

    /**
     * ⭐️ 使用额外的数组
     * 我们可以使用额外的数组来将每个元素放至正确的位置。
     * 用 len 表示数组的长度，我们遍历原数组，将原数组下标为 i 的元素放至新数组下标为 (i+k) mod n的位置，
     * 最后将新数组拷贝至原数组即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/rotate-array/solution/xuan-zhuan-shu-zu-by-leetcode-solution-nipk/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param array
     * @param k
     */
    private static void rotateArray1(int[] array,int k){
        int[] newArray = new int[array.length];
        int len = array.length;
        for (int i = 0; i < len; i++) {
            newArray[(i+k) % len] = array[i];
        }
        System.arraycopy(newArray,0,array,0,len);
    }

    /**
     * ⭐️ 数组翻转
     * 该方法基于如下的事实：当我们将数组的元素向右移动 k 次后，
     * 尾部 k mod n 个元素会移动至数组头部，其余元素向后移动 k mod n个位置。
     *
     * 翻转原数组
     * 翻转[0,k mod n-1]区间
     * 翻转[k mod n-1，n-1]区间
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/rotate-array/solution/xuan-zhuan-shu-zu-by-leetcode-solution-nipk/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param array
     * @param k
     */
    private static void rotateArray2(int[] array,int k){
        int len = array.length;
        k = k % len;
        reverse(array,0,len-1);
        reverse(array,0,k-1);
        reverse(array,k,len-1);
    }

    private static void reverse(int[] array,int start,int end){
        while (start < end){
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }
}
