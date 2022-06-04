package org.masteryourself.tutorial.redis.utils;

import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>description : RedisIdGenerateTest
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/5 12:32 AM
 */
@SpringBootTest
class RedisIdGenerateTest {

    @Resource
    private RedisIdGenerate redisIdGenerate;

    @org.junit.jupiter.api.Test
    void nextId() {
        System.out.println(redisIdGenerate.nextId("order"));
        System.out.println(redisIdGenerate.nextId("order"));
        System.out.println(redisIdGenerate.nextId("order"));
    }

}