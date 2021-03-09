package com.sitech.wth.test.mail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: wangth_oup
 * @date: 2020-10-23 10:48
 * @description: springboot邮件发送
 **/
@Component
public class MailApplication {

    private Logger logger = LogManager.getLogger(MailApplication.class);

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.fromMail.address}")
    private String from;
    @Value("${spring.mail.toMail.address}")
    private String to;

    public void sendSimpleMail(){
        //1.构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        //2.设置邮件主题
        message.setSubject("主题：这是一封测试邮件");
        //3.设置邮件发送者
        message.setFrom(from);
        //4.设置邮件接收者，可以有多个接收者
        message.setTo(to);
        //5.设置邮件抄送人，可以有多个抄送人
        message.setCc();
        //6.设置隐秘抄送人，可以有多个
        message.setBcc();
        //7.设置邮件发送日期
        message.setSentDate(new Date());
        //8.设置邮件的正文
        message.setText("内容：下班啦...");
        //9.发送邮件
        try {
            javaMailSender.send(message);
            logger.info("简单邮件已经发送。");
        } catch (MailException e) {
            logger.error("发送简单邮件时发生异常！", e);
        }
    }
}
