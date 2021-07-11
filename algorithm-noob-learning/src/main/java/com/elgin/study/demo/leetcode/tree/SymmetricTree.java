package com.elgin.study.demo.leetcode.tree;

import sun.jvm.hotspot.types.CIntegerField;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * ⭐️ 对称二叉树
 *
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *
 */
public class SymmetricTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode two1 = new TreeNode(2);
        TreeNode two2 = new TreeNode(2);
        TreeNode three1 = new TreeNode(3);
        TreeNode three2 = new TreeNode(3);
        TreeNode four1 = new TreeNode(4);
        TreeNode four2 = new TreeNode(4);

        root.left = two1;
        root.right = two2;

        two1.left = null;
        two1.right = three1;

        two2.left = null;
        two2.right = three2;

        System.out.println(isSymmetric(root));

        root.left = two1;
        root.right = two2;

        two1.left = three1;
        two1.right = four1;

        two2.left = four2;
        two2.right = three2;

        //System.out.println(isSymmetric(root));

    }

    private static boolean isSymmetric(TreeNode root){
        if(root == null){
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            //当前层次节点数
            int nodeNum = queue.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < nodeNum; i++) {
                TreeNode node = queue.poll();
                if(node == null){
                    subList.add(null);
                }else{
                    subList.add(node.val);
                }

                if(node !=null){
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            //判断subList是否回文数组
            if(subList.size()!=1 && subList.size()%2 !=0){
                return false;
            }

            boolean result = isPalindromeList(subList);
            if(!result){
                return false;
            }
        }
        return true;
    }

    private static boolean isPalindromeList(List<Integer> list){
        int i = 0;
        int j = list.size() -1;
        while (i<j){
            if(list.get(i) != list.get(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
