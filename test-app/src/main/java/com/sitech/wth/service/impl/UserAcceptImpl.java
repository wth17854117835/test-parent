package com.sitech.wth.service.impl;

import com.sitech.ijcf.message6.dt.in.InDTO;
import com.sitech.wth.dto.PubReq;
import com.sitech.wth.entity.mapdto.UserInfo;
import com.sitech.wth.entity.mapdto.UserInfoExample;
import com.sitech.wth.entity.mapper.UserInfoMapper;
import com.sitech.wth.service.inter.IUserAccept;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @author: wangth_oup
 * @date: 2020-07-20 15:25
 * @description:
 **/
@Service("UserAcceptImplSvc")
@RequestMapping("/user/api/accept")
@Api(tags = "用户信息", description = "用户信息管理", position = 1)
public class UserAcceptImpl implements IUserAccept {

    private Logger logger = LogManager.getLogger(HelloWorld.class);

    @Resource
    UserInfoMapper userInfoMapper;

    @PostMapping("/qryAllUser")
    @ApiOperation(value = "查询所有用户信息")
    @Override
    public List<UserInfo> qryAllUser() {
        List<UserInfo> userInfos = userInfoMapper.selectAll();
        logger.debug("======= qryAllUser() end... =======");
        return userInfos;
    }

    @PostMapping("/qryAll")
    @ApiOperation(value = "查询所有用户信息")
    @Override
    public List<UserInfo> qryAllUser(@RequestBody InDTO<PubReq<UserInfo>> in) {
        logger.debug("======= qryAllUser() start... =======");
        UserInfo userInfo = Optional.ofNullable(in)
                .flatMap(m -> Optional.ofNullable(m.getBody()))
                .flatMap(m -> Optional.ofNullable(m.getBusiInfo()))
                .orElse(null);
//        List<UserInfo> userInfos = userInfoMapper.selectAll();

        if (null != userInfo) {
            UserInfoExample example = new UserInfoExample();
            UserInfoExample.Criteria criteria = example.createCriteria();
            if (null != userInfo.getUserName() && !"".equals(userInfo.getUserName())) {
                criteria.andUserNameEqualTo(userInfo.getUserName());
            }
            if (null != userInfo.getAge()) {
                criteria.andAgeEqualTo(userInfo.getAge());
            }
            if(null != userInfo.getSex() && !"".equals(userInfo.getSex())){
                criteria.andSexEqualTo(userInfo.getSex());
            }
            if(null != userInfo.getPosition() && !"".equals(userInfo.getPosition())){
                criteria.andPositionEqualTo(userInfo.getPosition());
            }
            List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
            logger.debug("======= qryAllUser() end... =======");
            return userInfos;
        }
        return null;
    }

    @PostMapping("/insertUser")
    @ApiOperation(value = "新增用户信息")
    @Override
    public int insertUser(UserInfo userInfo) {
        logger.debug("======= insertUser() start... =======");
        if(null == userInfo.getUserId()){
            Random random = new Random();
            Long userId = System.currentTimeMillis() + random.nextInt(100);
//            SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
//            Date d = new Date();
//            Integer s = Integer.valueOf(sdf.format(d));
            userInfo.setUserId(userId);
        }
        int i = userInfoMapper.insert(userInfo);
        logger.debug("======= insertUser() end... =======");
        return i;
    }

    @PostMapping("/updateUser")
    @ApiOperation(value = "修改用户信息")
    @Override
    public int updateUserInfo(UserInfo userInfo) {
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        if (null != userInfo.getUserId()) {
            criteria.andUserIdEqualTo(userInfo.getUserId());
        }
//        int i = userInfoMapper.updateByExample(userInfo, example);
        int i = userInfoMapper.updateByExampleSelective(userInfo, example);
        return i;
    }

    @PostMapping("/delateUser")
    @ApiOperation(value = "删除用户信息")
    @Override
    public int delateUser(UserInfo userInfo) {
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        if (null != userInfo.getUserId()) {
            criteria.andUserIdEqualTo(userInfo.getUserId());
        }
        int i = userInfoMapper.deleteByExample(example);
        return i;
    }
}
