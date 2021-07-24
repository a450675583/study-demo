package com.elgin.study.demo.leetcode.tree;

/**
 * ⭐️ 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *    根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn08xg/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class CheckBinarySearchTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode four = new TreeNode(4);
        TreeNode six = new TreeNode(8);
        TreeNode three = new TreeNode(6);
        TreeNode seven = new TreeNode(9);

        root.left = four;
        root.right = six;

        six.left  = three;
        six.right = seven;

        System.out.println(isValidBST(root));
    }

    private static boolean isValidBST(TreeNode root) {
        return checkNode(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    private static boolean checkNode(TreeNode root,Long min,Long max) {
        if(root == null){
            return true;
        }
        if(root.val <= min || root.val >=max){
            return false;
        }
        return checkNode(root.left,min, (long) root.val)
                && checkNode(root.right, (long) root.val,max);
    }
}
