package com.elgin.study.demo.leetcode;

public class Demo implements Runnable{


    private Object lock;

    public Demo(Object lock){
        this.lock = lock;
    }

    private static  int value =0;

    private volatile static boolean isEnd = false;

    public static void main(String[] args) {
        while (true){
            Object lc = new Object();
            for (int i = 0 ; i <=5 ;i ++){
                new Thread(new Demo(lc)).start();
            }
            new Thread(() -> {
                synchronized (lc){
                    lc.notifyAll();
                }
            }).start();

            if(isEnd){
                value = 0;
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        synchronized (lock){
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("报数：" + value);
            value++;
            if(value == 5){
                isEnd = true;
            }
            lock.notify();
        }
    }
}
