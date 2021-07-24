package com.elgin.study.demo.java;

public class PrintABCUsingWaitNotify {

    private volatile int state = 1;
    private int times;
    private Object lock = new Object();

    public PrintABCUsingWaitNotify(int times){
        this.times = times;
    }

    private void print(String name,int targetNum){
        int i = 0;
        while (i < times){
            synchronized (lock){
                while (state % 3 != targetNum){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(name);
                i++;
                state++;
                lock.notifyAll();
            }
        }
    }


    public static void main(String[] args) {
        PrintABCUsingWaitNotify client = new PrintABCUsingWaitNotify(10);

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
