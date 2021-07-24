package com.elgin.study.demo.java;

import java.util.concurrent.locks.ReentrantLock;

public class PrintABCUsingLock {

    private volatile int state = 1;
    private int times;
    private ReentrantLock lock = new ReentrantLock();

    public PrintABCUsingLock(int times){
        this.times = times;
    }

    private void print(String name,int targetNum){
        int i = 0;
        while (i < times){
            lock.lock();
            if(state % 3 == targetNum){
                System.out.println(name);
                i++;
                state++;
            }
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        PrintABCUsingLock client = new PrintABCUsingLock(10);

        new Thread(() -> {
            client.print("A",1);
        }).start();

        new Thread(() -> {
            client.print("B",2);
        }).start();

        new Thread(() -> {
            client.print("C",0);
        }).start();
    }
}
