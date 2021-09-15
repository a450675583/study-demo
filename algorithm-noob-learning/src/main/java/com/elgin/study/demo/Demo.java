package com.elgin.study.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Demo implements Runnable{

    private CyclicBarrier barrierB;
    private CyclicBarrier barrierD;

    private String name;

    public Demo(CyclicBarrier barrierB,CyclicBarrier barrierD,String name){
        this.barrierB = barrierB;
        this.barrierD = barrierD;
        this.name = name;
    }

    @Override
    public void run() {
        //10点自由行
        System.out.println(name + ":10点A出发自由行开始");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //等待11点B集合完成
        try {
            System.out.println(name + ":11点到达B集合");
            barrierB.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }


        try {
            System.out.println(name + ":13点在D出口集合去其他景区到达");
            barrierD.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        CyclicBarrier barrierB = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("11点一起从B出发前往C");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("一起到达C");
            }
        });

        CyclicBarrier barrierD = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("13点在D出口集合一起坐大巴到其他景区");
            }
        });
        new Thread(new Demo(barrierB,barrierD,"张三")).start();
        new Thread(new Demo(barrierB,barrierD,"李四")).start();
        new Thread(new Demo(barrierB,barrierD,"王五")).start();
    }
}
