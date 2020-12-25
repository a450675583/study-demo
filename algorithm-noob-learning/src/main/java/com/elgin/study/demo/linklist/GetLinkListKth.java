package com.elgin.study.demo.linklist;
/*
1.链表的倒数第K个结点
输入一个链表，输出该链表中倒数第k个结点。
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author zxs
 * 2020/12/24
 */
public class GetLinkListKth {


    public static Node getNodeK1(Node node , int k){
        List<Node> list = new ArrayList<>();
        while (node!=null){
            list.add(node);
            node = node.next;
        }
        return list.get(list.size() - k);
    }


    public static Node getNodeK2(Node node , int k){
        int currentIndex = 1;
        Node cur = node;
        Node now = node;
        while (cur!=null && currentIndex <= k){
            cur = cur.next;
            currentIndex ++ ;
        }

        while (cur != null){
            cur = cur.next;
            now = now.next;
        }
        return now;
    }


    public static void main(String[] args) {
        Node node = new Node(0);
        Node head = node;
        for(int i = 1 ; i< 10; i++){
            node.next = new Node(i);
            node = node.next;
        }
        System.out.println(head);
        Node tmp = getNodeK1(head,10);
        System.out.println(tmp);

        Node k2 = getNodeK2(head,10);
        System.out.println(k2);
    }



}
