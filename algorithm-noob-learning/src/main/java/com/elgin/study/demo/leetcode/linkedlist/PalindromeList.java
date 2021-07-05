package com.elgin.study.demo.leetcode.linkedlist;

import java.util.Stack;

/**
 * ⭐️ 回文链表
 * 请判断一个链表是否为回文链表。
 *
 * 输入: 1->2
 * 输出: false
 *
 * 输入: 1->2->2->1
 * 输出: true
 *
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class PalindromeList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = null;

        System.out.println(isPalindrome(node1));
    }

    private static boolean isPalindrome(ListNode head) {
        ListNode tmp = head;
        Stack<Integer> stack = new Stack<>();
        while (tmp !=null){
            stack.push(tmp.val);
            tmp = tmp.next;
        }

        while (head != null){
            if(head.val != stack.pop()){
                return false;
            }
            head = head.next;
        }

        return true;
    }
}
