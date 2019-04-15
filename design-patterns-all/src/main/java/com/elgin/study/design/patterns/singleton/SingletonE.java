package com.elgin.study.design.patterns.singleton;

/**
 * "懒汉式" 枚举方式
 * 它更简洁，自动支持序列化机制，绝对防止多次实例化 （如果单例类实现了Serializable接口，默认情况下每次反序列化总会创建一个新的实例对象)
 */
public enum SingletonE {

    INSTANCE;

    public void doSomething(){
        System.out.println("doSomething....");
    }
}
