package org.masteryourself.tutorial.designpattern.login.strategy;

import org.masteryourself.tutorial.designpattern.login.anno.LoginStrategy;
import org.masteryourself.tutorial.designpattern.login.dto.LoginReq;
import org.masteryourself.tutorial.designpattern.login.dto.LoginResp;
import org.springframework.stereotype.Component;

/**
 * 策略:微信登录
 */
@Component
@LoginStrategy(name = "wechat")
public class WeChatGranter implements UserGranter {

    @Override
    public LoginResp login(LoginReq loginReq) {
        System.out.println("策略:登录方式为微信登录");
        // 执行业务操作
        return new LoginResp();
    }
}
