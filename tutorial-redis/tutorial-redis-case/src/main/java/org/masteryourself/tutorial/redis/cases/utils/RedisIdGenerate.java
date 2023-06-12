package org.masteryourself.tutorial.redis.cases.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * <p>description : RedisIdGenerate
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/5 12:15 AM
 */
@Component
public class RedisIdGenerate {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 基础时间
     * LocalDateTime.of(2022, 1, 1, 0, 0, 0).toEpochSecond(ZoneOffset.UTC)
     */
    private static final Long BEGIN_START_TIME = 1640995200L;

    public long nextId(String prefix) {
        // 1. 生成时间戳
        LocalDateTime now = LocalDateTime.now();
        long timestamp = now.toEpochSecond(ZoneOffset.UTC) - BEGIN_START_TIME;
        // 2. 生成序列号
        String date = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String key = RedisConstants.ID_GEN_KEY + prefix + ":" + date;
        long sequence = stringRedisTemplate.opsForValue().increment(key);
        // 3. 时间戳左移 32 位成高位, 低 32 位用 sequence 填充
        return timestamp << 32 | sequence;
    }

}
