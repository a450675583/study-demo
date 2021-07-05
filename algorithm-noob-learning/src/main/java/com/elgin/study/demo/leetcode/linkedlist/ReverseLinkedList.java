package com.elgin.study.demo.leetcode.linkedlist;

/**
 * ⭐️ 反转链表
 *
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 *
 * 输入：head = [1,2]
 * 输出：[2,1]
 *
 * 输入：head = []
 * 输出：[]
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;

        ListNode node = reverseLinkedList(node1);
        System.out.println(node);
    }

    private static ListNode reverseLinkedList(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode tmp = new ListNode(-1,null);
        while (head != null){
            ListNode next = head.next ;
            head.next = tmp.next;
            tmp.next = head;
            head = next;
        }
        return tmp.next;
    }


}
