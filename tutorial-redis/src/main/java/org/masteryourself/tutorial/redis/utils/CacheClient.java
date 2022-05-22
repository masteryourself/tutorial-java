package org.masteryourself.tutorial.redis.utils;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.masteryourself.tutorial.redis.dto.RedisData;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * <p>description : CacheClient
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/22 9:05 PM
 */
@Component
public class CacheClient {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ThreadPoolExecutor cacheBuildThreadPool;

    /**
     * 存储数据并设置 TTL 过期
     */
    public void set(String key, Object value, Long time, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), time, unit);
    }

    /**
     * 利用逻辑过期存储数据
     */
    public void setWithLogicalExpire(String key, Object value, Long time, TimeUnit unit) {
        // 设置逻辑过期
        RedisData redisData = new RedisData();
        redisData.setData(value);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));
        // 写入 Redis
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData));
    }

    /**
     * 缓存穿透解决方案:缓存空对象
     *
     * @param keyPrefix  key 前缀
     * @param id         主键标识
     * @param type       返回值类型
     * @param dbFallback 加载资源数据的地方, 是一个回调函数
     * @param time       缓存时间
     * @param unit       缓存时间单位
     * @param <R>        返回值类型
     * @param <ID>       主键类型
     * @return 结果
     */
    public <R, ID> R queryWithPassThrough(String keyPrefix, ID id, Class<R> type,
                                          Function<ID, R> dbFallback,
                                          Long time, TimeUnit unit) {
        // 1. 构建 redis key
        String key = keyPrefix + id;
        // 2. 从 redis 中查找缓存数据
        String json = stringRedisTemplate.opsForValue().get(key);
        // 3. 判断缓存中的数据是否为无效值
        if (Objects.equals(json, RedisConstants.CACHE_PASS_THROUGH_INVALID_FLAG)) {
            // 说明是无效值, 缓存穿透了, 直接返回
            return null;
        }
        // 4. 如果缓存中有数据且不为无效值直接返回
        if (StrUtil.isNotBlank(json)) {
            return JSONUtil.toBean(json, type);
        }
        // 5. 此时缓存中没有, 调用回调函数加载资源
        R result = dbFallback.apply(id);
        // 6. 如果不存在，则缓存无效值, 避免缓存穿透
        if (result == null) {
            this.set(key, RedisConstants.CACHE_PASS_THROUGH_INVALID_FLAG,
                    RedisConstants.CACHE_PASS_THROUGH_INVALID_TTL, TimeUnit.SECONDS);
            return null;
        }
        // 7. 如果加载到数据, 放入到缓存中
        this.set(key, result, time, unit);
        return result;
    }

    /**
     * 缓存击穿解决方案: 互斥锁
     *
     * @param keyPrefix  key 前缀
     * @param id         主键标识
     * @param type       返回值类型
     * @param dbFallback 加载资源数据的地方, 是一个回调函数
     * @param time       缓存时间
     * @param unit       缓存时间单位
     * @param <R>        返回值类型
     * @param <ID>       主键类型
     * @return 结果
     */
    public <R, ID> R queryWithMutex(String keyPrefix, ID id, Class<R> type,
                                    Function<ID, R> dbFallback,
                                    Long time, TimeUnit unit) {
        return null;
    }

    /**
     * 缓存击穿解决方案: 逻辑过期
     *
     * @param keyPrefix  key 前缀
     * @param id         主键标识
     * @param type       返回值类型
     * @param dbFallback 加载资源数据的地方, 是一个回调函数
     * @param time       缓存时间
     * @param unit       缓存时间单位
     * @param <R>        返回值类型
     * @param <ID>       主键类型
     * @return 结果
     */
    public <R, ID> R queryWithLogicalExpire(String keyPrefix, ID id, Class<R> type,
                                            Function<ID, R> dbFallback,
                                            Long time, TimeUnit unit) {
        return null;
    }

    private boolean tryLock(String key) {
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, "lock", 10, TimeUnit.SECONDS);
        return BooleanUtil.isTrue(flag);
    }

    private void unlock(String key) {
        stringRedisTemplate.delete(key);
    }

}
