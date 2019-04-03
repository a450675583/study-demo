package com.elgin.study.demo.stack;

/**
 * 用链表实现栈
 * @author 辰峰
 * @create 2019-04-03 22:44
 **/
public class StackImplWithLinkedList<E> {


    private Node<E> topNode;

    public StackImplWithLinkedList(){
    }


    /**
     * 元素入栈
     * @param e
     * @return
     */
    public boolean push(E e){
        Node<E> temp = topNode;
        this.topNode = new Node<>(e,temp);
        return true;
    }


    /**
     * 元素出栈
     * @return
     */
    public E pop(){
        if(topNode == null){
            throw new RuntimeException("there are no more elements in stack.....");
        }
        E e = topNode.element;
        Node<E> next = topNode.next;
        topNode = next;
        return e;
    }


    public static void main(String[] args) {
        StackImplWithLinkedList stack = new StackImplWithLinkedList();
        stack.push("sssss1");
        stack.push("sssss2");
        stack.push("sssss3");
        stack.push("sssss4");
        stack.push("sssss5");
        stack.push("sssss6");
        stack.push("sssss7");
        stack.push("sssss8");
        stack.push("sssss9");
        stack.push("sssss10");

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }


    /**
     * 链表节点类
     * @param <E>
     */
    class Node<E>{
        E element;
        Node<E> next;

        Node(E e, Node<E> next){
            this.element = e;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }
    }
}
