package com.elgin.study.demo.leetcode.linkedlist;

/**
 * ⭐️ 删除链表的倒数第N个节点
 *
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 */
public class RemoveLastKElement {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;

        ListNode head = removeNthFromEnd(node1,4);
        System.out.println(head);

        ListNode head1 = removeNthFromEnd(node4,1);
        System.out.println(head1);

    }

    private static ListNode removeNthFromEnd(ListNode head, int n){
        //计算链表的长度
        int total = 0;
        ListNode tmp = head;
        while (tmp != null){
            tmp = tmp.next;
            total++;
        }

        //倒数第n个，正数第  total - n + 1
        int seq = 0;
        int deleteIndex = total - n + 1;
        ListNode tmpNode = head;
        ListNode before = null;
        while (tmpNode != null){
            seq++;
            if(seq == deleteIndex){
                //删除逻辑
                if(before == null){
                    head = tmpNode.next;
                }else{
                    before.next = tmpNode.next;
                }
                break;
            }
            before = tmpNode;
            tmpNode = tmpNode.next;
        }
        return head;
    }
}
