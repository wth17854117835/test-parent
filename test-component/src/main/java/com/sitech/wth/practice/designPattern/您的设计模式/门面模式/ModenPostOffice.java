package com.sitech.wth.practice.designPattern.您的设计模式.门面模式;

/**
 * @author: wangth
 * @create: 2022-02-14 10:23
 */
public class ModenPostOffice {

    private LetterProcess letterProcess = new LetterProcessImpl();

    private Police letterPolice = new Police();

    //写信，封装，投递，一体化了
    public void sendLetter(String context, String address) {

        //帮你写信
        letterProcess.writeContext(context);

        //写好信封
        letterProcess.fillEnvelope(address);

        //警察进行安全检查
        //先写信，然后写信封，然后警察开始检查，然后才把信放到信封，然后发送出去，那这个变更对客户来说，
        //是透明的，他根本就看不到有人在检查他的邮件，他也不用了解，反正现代化的邮件都帮他做了
        letterPolice.checkLetter(letterProcess);

        //把信放到信封中
        letterProcess.letterIntoEnvelope();

        //邮递信件
        letterProcess.sendLetter();

    }
}
