package com.sitech.wth.entity.mapdto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "用户信息入参")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -1263415917596388419L;

    @ApiModelProperty(required = false, value = "用户ID", dataType = "int", example = "1", position = 0)
    @JSONField(name = "userId")
//    @JsonProperty("userId")
    private Long userId;

    @ApiModelProperty(required = false, value = "用户名称", dataType = "string", example = "wagnth", position = 1)
    @JSONField(name = "userName")
//    @JsonProperty("userName")
    private String userName;

    @JSONField(name = "age")
//    @JsonProperty("age")
    private Integer age;

    @JSONField(name = "sex")
//    @JsonProperty("sex")
    private String sex;

    @JSONField(name = "position")
//    @JsonProperty("position")
    private String position;

    @JSONField(name = "phoneNo")
//    @JsonProperty("phoneNo")
    private String phoneNo;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }
}