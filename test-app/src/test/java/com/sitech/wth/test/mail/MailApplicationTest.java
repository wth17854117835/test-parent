package com.sitech.wth.test.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author: wangth_oup
 * @date: 2020-10-23 10:57
 * @description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailApplicationTest {

    @Autowired
    MailApplication mailApplication;

    @Test
    public void sendSimpleMail() {
        mailApplication.sendSimpleMail();
    }
}