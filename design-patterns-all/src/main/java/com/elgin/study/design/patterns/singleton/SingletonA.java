package com.elgin.study.design.patterns.singleton;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 饿汉式
 * 线程安全
 * 所谓 “饿汉方式” 就是说JVM在加载这个类时就马上创建此唯一的单例实例，不管你用不用，先创建了再说，
 * 如果一直没有被使用，便浪费了空间，典型的空间换时间，每次调用的时候，就不需要再判断，节省了运行时间。
 */
public class SingletonA {

    private static SingletonA instance = new SingletonA();

    private  SingletonA(){

    }

    public static SingletonA getInstance(){
        return instance;
    }


    /**
     * 60个线程并发获取实例，均得到同一个对象。线程安全。
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
                SingletonA a = SingletonA.getInstance();
                System.out.println(a);
            });
        }
        pool.shutdown();
    }


}
