package com.elgin.study.design.patterns.factory;


/**
 * 邮件消息发送器
 */
public class MailSender implements Sender {
    @Override
    public void send() {
        System.out.println("send mail message");
    }
}
