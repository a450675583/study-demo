package com.elgin.study.design.patterns.factory;

/**
 * 简单工厂模式
 * 如果出现传递的字符串参数出现错误，则不能正确的创建对象，所以衍生出新的创建方式====>多个工厂方法模式
 */
public class SimpleFactory {

    public Sender getSender(String type){
        if("sms".equalsIgnoreCase(type)){
            return new SmsSender();
        }else if("mail".equalsIgnoreCase(type)){
            return new MailSender();
        }else {
            System.out.println("请输入正确的类型");
            return null;
        }
    }


    public static void main(String[] args) {
        SimpleFactory factory = new SimpleFactory();
        Sender sender = factory.getSender("sms");
        sender.send();
    }
}
