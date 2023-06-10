package org.masteryourself.tutorial.designpattern.login.strategy;

import org.masteryourself.tutorial.designpattern.login.anno.LoginStrategy;
import org.masteryourself.tutorial.designpattern.login.dto.LoginReq;
import org.masteryourself.tutorial.designpattern.login.dto.LoginResp;
import org.springframework.stereotype.Component;

/**
 * 策略:短信登录
 */
@Component
@LoginStrategy(name = "sms")
public class SmsGranter implements UserGranter {

    @Override
    public LoginResp login(LoginReq loginReq) {
        System.out.println("策略:登录方式为短信登录");
        // 执行业务操作
        return new LoginResp();
    }

}
