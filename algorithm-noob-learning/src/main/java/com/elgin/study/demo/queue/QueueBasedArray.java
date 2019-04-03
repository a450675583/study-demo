package com.elgin.study.demo.queue;

import java.util.Arrays;

/**
 *
 * @author 辰峰
 * @create 2019-04-03 23:13
 **/
public class QueueBasedArray{

    /**
     * 数组初始化大小
     */
    private static final Integer DEFAULT_SIZE = 16;

    /**
     * 存放数据数组容器
     */
    transient Object[] tableData;

    /**
     * 队头索引
     */
    private int headIndex;

    /**
     * 队尾巴索引
     */
    private int tailIndex;

    /**
     * 队列元素数量
     */
    private int size;

    public QueueBasedArray(){
        this.tableData = new Object[DEFAULT_SIZE];
    }

    /**
     * 向队列中添加元素
     * @param o
     * @return
     */
    public boolean add(Object o) {
        int length = tableData.length;
        if(size == length){
            //数组已满 ，扩容
            int newSize = length  + (length>>1== 0 ? 1 : length>>1) ;
            tableData = Arrays.copyOf(tableData,newSize);
        }
        tableData[tailIndex++] = o ;
        size++;
        return true;
    }

    /**
     * 向队列中添加元素
     * @param o
     * @return
     */
    public boolean offer(Object o) {
        return add(o);
    }

    /**
     * 移除队头元素
     * 队列为空时，抛出异常
     * @return
     */
    public Object remove() {
        if(size == 0){
            throw  new RuntimeException("there are no more elements in queue");
        }
        Object o = tableData[headIndex];
        tableData[headIndex] = null;
        System.arraycopy(tableData,headIndex+1,tableData,0,--size);
        return o;
    }


    /**
     * 移除队头元素
     * 队列为空时，返回null
     * @return
     */
    public Object poll() {
        if(size > 0){
            return remove();
        }
        return null;
    }

    /**
     * 获取队头元素
     * 不移除
     * 队列为空时，抛出异常
     * @return
     */
    public Object element() {
        if(size == 0){
            throw  new RuntimeException("there are no more elements in queue");
        }
        Object o = tableData[headIndex];
        return o;
    }

    /**
     * 获取队头元素
     * 不移除
     * 队列为空时，返回null
     * @return
     */
    public Object peek() {
        if(size>0){
            return element();
        }
        return null;
    }


    /**
     * queue 容量
     * @return
     */
    public int size(){
        return size;
    }


    public static void main(String[] args) {
        QueueBasedArray queue = new QueueBasedArray();
        for(int i=0; i<=20 ; i++){
            queue.add("ssss" + i);
        }

        int queueSize = queue.size();
        while (queueSize>=0){
            System.out.println(queue.remove());
//            System.out.println(queue.poll());
//            System.out.println(queue.element());
//            System.out.println(queue.peek());
        }


    }
}
