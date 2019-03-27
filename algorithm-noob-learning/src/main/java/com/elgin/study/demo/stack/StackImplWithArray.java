package com.elgin.study.demo.stack;

import java.util.Arrays;

/**
 * 数组实现栈
 * 栈的特点：后进先出
 * 用数组的size标记进出栈的元素位置
 * @author 辰峰
 * @create 2019-03-27 23:08
 **/
public class StackImplWithArray {

    /**
     * 数组初始化大小
     */
    private static final Integer DEFAULT_SIZE = 16;

    /**
     * 元素容器
     */
    transient Object[] array;

    /**
     * 栈中元素数
     */
    private int size;

    public StackImplWithArray(){
        this.array = new Object[DEFAULT_SIZE];
    }


    public StackImplWithArray(int capacity){
        if(capacity > 0){
            this.array = new Object[capacity];
        }else if(capacity == 0) {
            this.array = new Object[DEFAULT_SIZE];
        }else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    capacity);
        }
    }

    /**
     * 进栈
     * @param obj
     * @return
     */
    public boolean push(Object obj){
        // 容量判断,不足时1.5扩容(参照 ArrayList)
        int oldCapacity = array.length;
        if(oldCapacity == size ){

            Integer newCapacity = oldCapacity + (oldCapacity>>1 == 0 ? 1 :oldCapacity>>1 );
            array = Arrays.copyOf(array,newCapacity);
        }

        array[size++] = obj ;
        return true;

    }


    /**
     * 出栈
     * @return
     */
    public Object pop(){
        if(size>0){
            return  array[--size];
        }
        return null;
    }


    public static void main(String[] args) {
        StackImplWithArray stack = new StackImplWithArray(1);
        stack.push(3);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push("sss");
        stack.push("111");
        stack.push("234");
        stack.push("er");

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





}
