package com.elgin.study.design.patterns.factory;

/**
 * 工厂方法模式
 * 一般为静态工厂方法模式
 */
public class FactoryMethod {

    public static Sender getSmsSender(){
        return new SmsSender();
    }

    public static Sender getMailSender(){
        return  new MailSender();
    }

    public static void main(String[] args) {
        Sender sender1 = FactoryMethod.getMailSender();
        Sender sender2 = FactoryMethod.getSmsSender();
        sender1.send();
        sender2.send();
    }
}
