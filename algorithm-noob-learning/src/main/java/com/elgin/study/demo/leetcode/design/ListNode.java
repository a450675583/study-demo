package com.elgin.study.demo.leetcode.design;

public class ListNode {

    int val;
    int min;
    ListNode next;
    public ListNode(int x) {
        val = x;
    }

    ListNode(int val, ListNode next) {
        this.val = val; this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", min=" + min +
                ", next=" + next +
                '}';
    }
}
