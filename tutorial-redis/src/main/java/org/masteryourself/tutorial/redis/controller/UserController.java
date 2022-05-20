package org.masteryourself.tutorial.redis.controller;

import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.redis.dto.Result;
import org.masteryourself.tutorial.redis.service.UserService;
import org.masteryourself.tutorial.redis.utils.UserHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>description : 用户相关请求
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/19 7:11 PM
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("sendCode")
    public Result sendCode(@RequestParam("phone") String phone) {
        return userService.sendCode(phone);
    }

    @PostMapping("/login")
    public Result login(String phone, String code) {
        return userService.login(phone, code);
    }

    @PostMapping("/me")
    public Result me() {
        return Result.ok(UserHolder.getUser());
    }

}
