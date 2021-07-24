package com.elgin.study.demo.leetcode.tree;

public class MinDepth {

    public static void main(String[] args) {

    }

    private static int minDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }
        int leftDepth = root.left!=null ? minDepth(root.left) + 1 : 0;
        int rightDepth = root.right!=null ? minDepth(root.right) + 1 : 0;
        if(leftDepth == 0){
            return rightDepth;
        }
        if(rightDepth == 0){
            return  leftDepth;
        }
        return leftDepth < rightDepth ? leftDepth : rightDepth;
    }
}
