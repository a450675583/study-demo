package com.elgin.study.demo.leetcode.twoPoints;

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

    /**
     * 双指针删除链表倒数第N个元素
     * @param head
     * @param n
     * @return
     */
    private static ListNode removeNthFromEnd(ListNode head, int n){
        ListNode fast = head;
        ListNode slow = head;

        //快指针先走n步
        for(int i=0 ; i < n ; i++){
            fast = fast.next;
        }

        if(fast == null){
            //说明删除的是头节点
            return head.next;
        }

        //慢指针 再走  用fast控制slow的长度  即 total - n
        while (fast !=null){
            fast = fast.next;
            slow = slow.next;
        }

        //slow为待删除元素的pre 节点
        slow.next = slow.next.next;

        return head;
    }
}
