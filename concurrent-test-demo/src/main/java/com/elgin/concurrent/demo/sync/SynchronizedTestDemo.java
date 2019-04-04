package com.elgin.concurrent.demo.sync;

/**
 * javap -c  SynchronizedTestDemo.class
 * 查看字节码
 */
public class SynchronizedTestDemo {

    public void  test(){
        synchronized (this){
            System.out.println("Hello World");
        }
    }
}
