package org.masteryourself.tutorial.redis.cases.utils;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * <p>description : RedisLock
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/5 9:23 PM
 */
@Slf4j
public class RedisLock implements Lock {

    private final String name;

    private final StringRedisTemplate stringRedisTemplate;

    public RedisLock(String name, StringRedisTemplate stringRedisTemplate) {
        this.name = name;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    private static final String THREAD_ID_PREFIX = UUID.fastUUID().toString(true);

    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT;

    static {
        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        UNLOCK_SCRIPT.setLocation(new ClassPathResource("lua/unlock.lua"));
        UNLOCK_SCRIPT.setResultType(Long.class);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        String threadId = THREAD_ID_PREFIX + "_" + Thread.currentThread().getId();
        // 加锁时需要把自己的线程 id 存入, 防止别人调用了解锁
        return Boolean.TRUE.equals(
                stringRedisTemplate.opsForValue().setIfAbsent(
                        RedisConstants.LOCK_KEY + name,
                        threadId, unit.toMillis(time), TimeUnit.MILLISECONDS));
    }

    @Override
    public void unlock() {
        String threadId = THREAD_ID_PREFIX + "_" + Thread.currentThread().getId();
        stringRedisTemplate.execute(
                UNLOCK_SCRIPT,
                Collections.singletonList(RedisConstants.LOCK_KEY + name),
                threadId);
    }

}
