package com.sitech.wth.practice.designPattern.您的设计模式.门面模式;

/**
 * @author: wangth
 * @create: 2022-02-14 10:29
 *
 * 寄往 God Province（上帝省）的邮件都必须进行安全检查
 */
public class Police {

    public void checkLetter(LetterProcess letterProcess) {
        System.out.println("警察进行安全检查");
    }
}
