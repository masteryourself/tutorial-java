package org.masteryourself.tutorial.designpattern.login.strategy;

import org.masteryourself.tutorial.designpattern.login.anno.LoginStrategy;
import org.masteryourself.tutorial.designpattern.login.dto.LoginReq;
import org.masteryourself.tutorial.designpattern.login.dto.LoginResp;
import org.springframework.stereotype.Component;

/**
 *策略：账号登录
 */
@Component
@LoginStrategy(name = "account")
public class AccountGranter implements UserGranter{

	@Override
	public LoginResp login(LoginReq loginReq) {
		System.out.println("策略:登录方式为账号登录");
		// 执行业务操作
		return new LoginResp();
	}

}
