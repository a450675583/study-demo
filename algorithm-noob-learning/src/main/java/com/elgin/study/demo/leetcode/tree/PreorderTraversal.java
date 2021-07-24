package com.elgin.study.demo.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * ⭐️ 二叉树的先序遍历
 *
 */
public class PreorderTraversal {

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

        preorderTraversal(root,result);

        System.out.println(result);
    }

    private static void preorderTraversal(TreeNode node, List<Integer> list){
        if(node == null){
            return;
        }
        //先根节点
        list.add(node.val);

        //左子树
        if(node.left != null){
            preorderTraversal(node.left,list);
        }

        //右子树
        if(node.right != null){
            preorderTraversal(node.right,list);
        }
    }
}
