package com.elgin.study.demo.leetcode.linkedlist;

/**
 * ⭐️ 合并2个有序链表
 *
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 *
 * 输入：l1 = [], l2 = []
 * 输出：[]
 */
public class MergeTwoSortedList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = null;

        ListNode nodea = new ListNode(2);
        ListNode nodeb = new ListNode(5);
        ListNode nodec = new ListNode(8);
        nodea.next = nodeb;
        nodeb.next = nodec;
        nodec.next = null;

        ListNode node = mergeTwoLists(node1, nodea);
        System.out.println(node);
    }

    private static ListNode mergeTwoLists(ListNode linked1, ListNode linked2){
        if(linked1 == null){
            return linked2;
        }

        if(linked2 == null){
            return  linked1;
        }

        ListNode dump = new ListNode(0);
        ListNode tmpNode = dump;
        while (linked1 !=null && linked2!=null){
            if(linked1.val < linked2.val){
                tmpNode.next = linked1;
                linked1 = linked1.next;
            }else{
                tmpNode.next = linked2;
                linked2 = linked2.next;
            }
            tmpNode = tmpNode.next;
        }
        if(linked1 != null){
            tmpNode.next = linked1;
        }
        if(linked2 != null){
            tmpNode.next = linked2;
        }
        return dump.next;
    }
}
