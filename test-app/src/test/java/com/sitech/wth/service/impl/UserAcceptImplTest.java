package com.sitech.wth.service.impl;

import com.sitech.wth.entity.mapdto.UserInfo;
import com.sitech.wth.service.inter.IUserAccept;
import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author: wangth_oup
 * @date: 2020-07-21 10:02
 * @description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAcceptImplTest {

    @Resource
    IUserAccept iUserAccept;

    @Test
    public void qryAllUser() {
        UserInfo userInfo = new UserInfo();

//        List<UserInfo> userInfos = iUserAccept.qryAllUser(userInfo);
        System.out.println("=====");
    }

    @Test
    public void insertUser() {
        LocalDate date = LocalDate.now();//2020-07-22
        LocalTime time = LocalTime.now();//15:54:01.508
        LocalDateTime dateTime = LocalDateTime.now();//2020-07-22T15:54:03.136
        System.out.println(date);
        System.out.println(time);
        System.out.println(dateTime);

        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        Date d = new Date();
        String s = sdf.format(d);
        System.out.println(s);//200722155821

        Random random = new Random();
        // id=时间戳+一个100以内的随机数
        Long id = System.currentTimeMillis() + random.nextInt(100);
        System.out.println(id);


        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(3l);
        userInfo.setUserName("IG the shy");
        userInfo.setAge(25);
        userInfo.setSex("男");
        userInfo.setPosition("上单");
        userInfo.setPhoneNo("13200001111");
        int i = iUserAccept.insertUser(userInfo);
        System.out.println(i);
    }

    @Test
    public void updateUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(3l);
        userInfo.setAge(18);
        userInfo.setSex("女");
        int i = iUserAccept.updateUserInfo(userInfo);
        System.out.println(i);
    }

    @Test
    public void delateUser() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(3l);
        int i = iUserAccept.delateUser(userInfo);
        System.out.println(i);
    }
}