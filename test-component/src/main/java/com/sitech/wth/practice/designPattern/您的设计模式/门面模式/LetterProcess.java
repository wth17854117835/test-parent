package com.sitech.wth.practice.designPattern.您的设计模式.门面模式;

/**
 * @author: wangth
 * @create: 2022-02-14 10:20
 * 定义一个写信的过程
 */
public interface LetterProcess {

    //首先要写信的内容
    public void writeContext(String context);

    //其次写信封
    public void fillEnvelope(String address);

    //把信放到信封里
    public void letterIntoEnvelope();

    //然后邮递
    public void sendLetter();

}
