package com.elgin.study.demo.leetcode.tree;

/**
 * ⭐️ 根节点到叶节点数字之和
 *
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 *
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 *
 * 叶节点 是指没有子节点的节点。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SumNumbers {

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

        System.out.println(sumNumbers(root));
    }

    public static int sumNumbers(TreeNode root) {
        return getSum(root,0);
    }

    /**
     * 递归不止 只能传当前node  还可以传当前node的一些状态值
     * @param node
     * @param value
     * @return
     */
    private static int getSum(TreeNode node,int value){
        if(node == null){
            return 0;
        }
        int currentValue = value * 10 + node.val;
        if(node.left == null && node.right == null){
            return currentValue;
        }
        return getSum(node.left, currentValue) + getSum(node.right,currentValue);
    }
}
