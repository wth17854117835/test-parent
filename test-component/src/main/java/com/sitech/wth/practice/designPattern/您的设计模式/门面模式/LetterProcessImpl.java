package com.sitech.wth.practice.designPattern.您的设计模式.门面模式;

/**
 * @author: wangth
 * @create: 2022-02-14 10:20
 */
public class LetterProcessImpl implements LetterProcess{

    @Override
    public void writeContext(String context) {
        System.out.println("填写信的内容...." + context);
    }

    @Override
    public void fillEnvelope(String address) {
        System.out.println("填写收件人地址及姓名...." + address);
    }

    @Override
    public void letterIntoEnvelope() {
        System.out.println("把信放到信封中....");
    }

    @Override
    public void sendLetter() {
        System.out.println("邮递信件...");
    }
}
