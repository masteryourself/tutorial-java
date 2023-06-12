package org.masteryourself.tutorial.redis.sentinel.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>description : TestController
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/9 5:32 PM
 */
@RestController
public class TestController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/get/{key}")
    public String get(@PathVariable String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @GetMapping("/set/{key}/{value}")
    public String set(@PathVariable String key, @PathVariable String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        return "success";
    }

}
