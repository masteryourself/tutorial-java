package org.masteryourself.tutorial.redis.service;

import org.masteryourself.tutorial.redis.dto.Result;

/**
 * <p>description : UserService
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/19 7:21 PM
 */
public interface UserService {

    Result sendCode(String phone);

    Result login(String phone, String code);
}
