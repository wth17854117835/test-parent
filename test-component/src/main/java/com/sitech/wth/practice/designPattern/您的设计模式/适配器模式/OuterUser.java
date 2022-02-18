package com.sitech.wth.practice.designPattern.您的设计模式.适配器模式;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wangth
 * @create: 2022-02-14 11:21
 */
public class OuterUser implements IOuterUser{

    @Override
    public Map getUserBaseInfo() {
        HashMap<String,String> baseInfoMap = new HashMap<>();
        baseInfoMap.put("userName", "这个员工叫混世魔王....");
        baseInfoMap.put("mobileNumber", "这个员工电话是....");
        return baseInfoMap;
    }

    @Override
    public Map getUserOfficeInfo() {
        HashMap<String,String> homeInfoMap = new HashMap<>();
        homeInfoMap.put("jobPosition", "这个人的职位是BOSS....");
        homeInfoMap.put("officeTelNumber", "员工的办公电话是....");
        return homeInfoMap;
    }

    @Override
    public Map getUserHomeInfo() {
        HashMap<String,String> officeInfoMap = new HashMap<>();
        officeInfoMap.put("homeTelNumber", "员工的家庭电话是....");
        officeInfoMap.put("homeAddress", "员工的家庭地址是....");
        return officeInfoMap;
    }
}
