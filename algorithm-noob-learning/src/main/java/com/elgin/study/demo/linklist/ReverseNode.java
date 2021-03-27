package com.elgin.study.demo.linklist;

/**
 * @author zxs
 * 2021/3/26
 */
public class ReverseNode {


    public static void main(String[] args) {
        Node node = new Node(0);
        Node head = node;
        for(int i = 1 ; i< 10; i++){
            node.next = new Node(i);
            node = node.next;
        }

        System.out.println(head);
        Node result = reverseNode(head);
        System.out.println(result);
    }


    private static Node reverseNode(Node head){
        Node temp = new Node(-1);
        while (head != null){
            Node next = head.next;
            head.next = temp.next;
            temp.next = head;
            head = next;
        }
        return temp.next;
    }
}
