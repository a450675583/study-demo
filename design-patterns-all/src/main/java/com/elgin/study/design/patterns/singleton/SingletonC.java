package com.elgin.study.design.patterns.singleton;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * “懒汉式”
 * 双重检查加锁（double check locking）
 * 首先检查是否已创建，如果未创建，才同步加锁进行创建。这样，只会同步一次。
 * 另外要特别注意instance的声明必须使用volatile关键字修饰。因为
 * 当线程A第一次执行到20行代码的时候，线程B同事执行17行，这个时候线程A可能会得到instance!=null的情况，然后返回的是一个空实例。
 * 因为真正的情况线程A还没有初始化完毕instance的实例。
 * 那么为什么线程B为什么会得到instance不为空的实例(实则是为空的实例)呢？
 * 我们剖析 instance = new SingletonC(); 这一行的代码，隐藏了底层实现过程：
 * 1.首先jvm要为这个对象实例分配内存空间
 * 2.初始化这个对象
 * 3.将instance指向内存地址
 * 由于在处理器阶段时候，会出现系统优化的重排序问题，但要符合一些规则才能重排需，首先是一些关键字不允许重排序等其他，其次要保证重排序后的结果要一致（单线程情况下）。
 * 所以在上述步骤2，3会被重排序，那么就会出现如下新的排序：
 * 1.首先jvm分配内存空间
 * 2.instance指向内存的地址
 * //这个时候线程B进来了，要执行实例是否为空的判断，得到instance引用不为空，因为他已经指向了一个内存的地址了。
 * 3.初始化instance对象
 */
public class SingletonC {

    private volatile static SingletonC instance;
    private SingletonC(){}

    public static SingletonC getInstance(){
        if(instance == null){
            synchronized (SingletonC.class){
                if(instance == null){
                    instance = new SingletonC();
                }
            }
        }
        return instance;
    }
}
