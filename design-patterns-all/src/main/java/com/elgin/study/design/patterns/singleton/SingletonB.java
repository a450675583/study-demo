package com.elgin.study.design.patterns.singleton;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 懒汉式
 * 非线程安全
 * 所谓 “ 懒汉式” 就是说单例实例在第一次被使用时构建，而不是在JVM在加载这个类时就马上创建此唯一的单例实例。
 *
 * 但是上面这种方式很明显是线程不安全的，如果多个线程同时访问getInstance()方法时就会出现问题。
 * 如果想要保证线程安全，一种比较常见的方式就是在getInstance() 方法前加上synchronized关键字。
 */
public class SingletonB {

    private static SingletonB instance;

    private SingletonB(){}

    public static SingletonB getInstance(){
        if(instance ==null){
            instance = new SingletonB();
        }
        return instance;
    }

    /**
     * 60个线程并发获取实例，可能得到不是同一个对象。线程不安全。
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(60);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(60);
        for(int i=0; i< 60 ; i++){
            pool.execute(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                SingletonB a = SingletonB.getInstance();
                System.out.println(a);
            });
        }
        pool.shutdown();
    }
}
