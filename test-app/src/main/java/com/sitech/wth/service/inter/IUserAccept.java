package com.sitech.wth.service.inter;

import com.sitech.ijcf.message6.dt.in.InDTO;
import com.sitech.wth.dto.PubReq;
import com.sitech.wth.entity.mapdto.UserInfo;

import java.util.List;

/**
 * @author: wangth_oup
 * @date: 2020-07-20 15:26
 * @description:
 **/
public interface IUserAccept {

    //查询所有用户信息
    List<UserInfo> qryAllUser();
    List<UserInfo> qryAllUser(InDTO<PubReq<UserInfo>> userInfo);

    //新增用户信息
    int insertUser(UserInfo userInfo);

    //修改用户信息
    int updateUserInfo(UserInfo userInfo);

    //删除用户信息
    int delateUser(UserInfo userInfo);
}
