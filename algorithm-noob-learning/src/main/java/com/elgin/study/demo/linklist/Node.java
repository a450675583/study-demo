package com.elgin.study.demo.linklist;

import java.util.List;

/**
 * @author zxs
 * 2020/12/24
 */
public class Node {

    public Integer item;
    public Node next;

    public Node(){

    }
    public Node(Integer element) {
        this.item = element;
    }

    @Override
    public String toString() {
        return "Node{" +
                "item=" + item +
                ", next=" + next +
                '}';
    }

    public static Node createNode(List<Integer> list){
        Node head = new Node(list.get(0));
        if(list.size()<=1){
            return head;
        }
        for(int i =1 ;i < list.size() ; i++){
            Node temp = new Node(list.get(i));;
            temp.next = head;
            head =temp;
        }
        return head;
    }
}
