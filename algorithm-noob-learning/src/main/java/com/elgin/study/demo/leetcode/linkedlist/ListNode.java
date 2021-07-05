package com.elgin.study.demo.leetcode.linkedlist;

public class ListNode {

    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }

    ListNode(int val, ListNode next) {
        this.val = val; this.next = next;
    }


    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
