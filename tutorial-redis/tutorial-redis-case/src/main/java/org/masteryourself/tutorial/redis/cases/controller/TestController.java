package org.masteryourself.tutorial.redis.cases.controller;

import org.masteryourself.tutorial.redis.cases.dto.Result;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>description : TestController
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/7/13 17:05
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/redis")
    public Result update() {
        stringRedisTemplate.opsForHash().get("chushibiao", "k67121");
        return Result.ok();
    }

}
