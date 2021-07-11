package com.elgin.study.demo.leetcode.tree;

/**
 * ⭐️ 将有序数组转换为二叉搜索树
 *
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 *
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xninbt/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class TransferArrayToTree {

    public static void main(String[] args) {

    }

    private static TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0){
            return null;
        }
        return productTree(nums,0,nums.length-1);
    }

    private static TreeNode productTree(int[] arr,int start,int end){
        if(start > end){
            return null;
        }
        int mid = start + (end - start)/2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = productTree(arr,start,mid-1);
        root.right = productTree(arr,mid+1,end);
        return root;
    }
}
