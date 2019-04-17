package com.elgin.study.design.patterns.factory;

/**
 * 短信消息发送器
 */
public class SmsSender implements Sender {

    @Override
    public void send() {
        System.out.println("send sms message");
    }
}
