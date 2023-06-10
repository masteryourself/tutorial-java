package org.masteryourself.tutorial.designpattern.login.dto;

import lombok.Data;

@Data
public class LoginReq {

    /**
     * account : 用户名密码登录
     * sms : 手机验证码登录
     * we_chat : 微信登录
     */
    private String type;
}
