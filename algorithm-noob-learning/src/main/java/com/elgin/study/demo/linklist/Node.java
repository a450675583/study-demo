package com.elgin.study.demo.linklist;

/**
 * @author zxs
 * 2020/12/24
 */
public class Node<E> {

    E item;
    Node<E> next;

    Node(E element) {
        this.item = element;
    }

    @Override
    public String toString() {
        return "Node{" +
                "item=" + item +
                ", next=" + next +
                '}';
    }
}
