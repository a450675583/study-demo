package com.elgin.study.demo.linklist;

import com.google.common.collect.Lists;

/**
 * 链表的第一个交点（交点node为同一个node对象）
 * @author zxs
 * 2021/3/27
 */
public class GetIntersectionNode {

    public static void main(String[] args) {
        Node a = Node.createNode(Lists.newArrayList(2,7,5,8));
        Node b = Node.createNode(Lists.newArrayList(1,3));
        Node x = new Node(10);
        a.next = x;
        b.next = x;
        System.out.println(a);
        System.out.println(b);
        Node intersectionNode = getIntersectionNode(a, b);
        System.out.println(intersectionNode);
    }


    private static Node getIntersectionNode(Node a,Node b){
        Node n1 = a ;
        Node n2 = b;
        while (n1 != n2){
            n1 = (n1==null) ? b : n1.next;
            n2 = (n2==null) ? a : n2.next;
        }
        return n1;
    }
}
