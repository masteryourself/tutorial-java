package org.masteryourself.tutorial.designpattern.login.strategy;

import org.masteryourself.tutorial.designpattern.login.dto.LoginReq;
import org.masteryourself.tutorial.designpattern.login.dto.LoginResp;

/**
 * 抽象策略类
 */
public interface UserGranter {

    /**
     * 获取数据
     *
     * @param loginReq 传入的参数
     *                 0:账号密码
     *                 1:短信验证
     *                 2:微信授权
     * @return map值
     */
    LoginResp login(LoginReq loginReq);

}
