package com.elgin.study.demo.leetcode.design;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * LRU cache
 */
public class LRUCache<K,V> {

    public static void main(String[] args) {
        LRUCache<Integer,Integer> cache = new LRUCache<>(5);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);
        cache.put(5,5);
        cache.put(6,6);

        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println("");
    }

    private Integer capacity;
    private Map<K,DNode> dataMap = new ConcurrentHashMap<>();
    private DNode<K,V> head = new DNode<>();
    private DNode<K,V> tail = new DNode<>();
    private Integer len;

    public LRUCache(Integer capacity){
        this.capacity = capacity;
        this.len = 0;
        head.next = tail;
        tail.prev = head;
    }

    public V get(K key){
        DNode<K,V> dNode  = dataMap.get(key);
        if(dNode != null){
            moveToHead(dNode);
            return dNode.value;
        }
        return null;
    }

    public void put(K key,V value){
        DNode dNode = dataMap.get(key);
        if(dNode != null){
            //更新值
            dNode.value = value;
            moveToHead(dNode);
            return;
        }else{
            //超过容量,先淘汰尾巴，再加入链表头部
            if(len == capacity){
                dataMap.remove(tail.prev.key);
                removeTail();
            }
            DNode node = new DNode(key,value);
            //加入map
            dataMap.put(key,node);
            //加入链表头部
            addNodeToHead(node);
        }
    }


    private void addNodeToHead(DNode dNode){
        dNode.next = head.next;
        dNode.prev = head;
        head.next.prev = dNode;
        head.next = dNode;
        len++;
    }

    private void removeTail(){
        if(len > 0){
            DNode dNode = tail.prev;
            dNode.prev.next = tail;
            tail.prev = dNode.prev;
            dNode = null;
            len--;
        }
    }


    private void moveToHead(DNode dNode){
        if(dNode == head){
            //本身是头结点，不处理
            return;
        }
        //dNode节点处理
        dNode.next.prev = dNode.prev;
        dNode.prev.next = dNode.next;

        //头节点改为head
        dNode.next = head.next;
        head.next.prev = dNode;
        dNode.prev = head;
        head.next = dNode;
    }


    private class DNode<K,V>{
        private DNode prev;
        private DNode next;
        private K key;
        private V value;
        public DNode(){}
        public DNode(K key,V value){
            this.key = key;
            this.value = value;
        }
    }
}
