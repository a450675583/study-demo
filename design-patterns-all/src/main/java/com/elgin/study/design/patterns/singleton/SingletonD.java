package com.elgin.study.design.patterns.singleton;

/**
 * "懒汉式" 私有静态内部类
 * 只有通过显式调用 getInstance 方法时，才会显式装载 SingletonHolder 类，从而实例化 instance
 */
public class SingletonD {

    private SingletonD(){}

    private static class SingletonHoder{
        private static SingletonD instance = new SingletonD();
    }

    public static SingletonD getInstance(){
        return SingletonHoder.instance;
    }
}
