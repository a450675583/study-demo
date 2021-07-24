package com.elgin.study.demo.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * ⭐️ 二叉树的中序遍历
 */
public class InorderTraversal {

    public static void main(String[] args) {
        List<Integer> result = new ArrayList<>();

        TreeNode root = new TreeNode(5);
        TreeNode four = new TreeNode(4);
        TreeNode six = new TreeNode(8);
        TreeNode three = new TreeNode(6);
        TreeNode seven = new TreeNode(9);

        root.left = four;
        root.right = six;

        six.left  = three;
        six.right = seven;

        inorderTraversal(root,result);

        System.out.println(result);

    }

    private static void inorderTraversal(TreeNode root,List<Integer> list){
        if(root == null){
            return;
        }
        //左子树
        if(root.left != null){
            inorderTraversal(root.left,list);
        }

        //根节点
        list.add(root.val);

        //右子树
        if(root.right!=null){
            inorderTraversal(root.right,list);
        }
    }
}
