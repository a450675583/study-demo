package com.elgin.study.demo.linklist;

import java.util.Stack;

/**
 * 从尾到头打印链表
 * @author zxs
 * 2020/12/24
 */
public class PrintLinkListKth {

    public static void main(String[] args) {
        Node node = new Node(0);
        Node head = node;
        for(int i = 1 ; i< 10; i++){
            node.next = new Node(i);
            node = node.next;
        }
        printNode2(head);
    }

    private static void printNode(Node head){
        Stack<Node> stack = new Stack();
        while (head != null){
            stack.push(head);
            head = head.next;
        }

        while (!stack.isEmpty()){
            Node pop = stack.pop();
            System.out.println(pop.item);
        }
    }


    private static void printNode2(Node head){
        if (head != null){
            if(head.next !=null){
                printNode2(head.next);
            }
            System.out.println(head.item);
        }
    }
}
