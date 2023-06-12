package org.masteryourself.tutorial.redis.cases.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.redis.cases.domain.Shop;
import org.masteryourself.tutorial.redis.cases.dto.Result;
import org.masteryourself.tutorial.redis.cases.mapper.ShopMapper;
import org.masteryourself.tutorial.redis.cases.service.ShopService;
import org.masteryourself.tutorial.redis.cases.utils.CacheClient;
import org.masteryourself.tutorial.redis.cases.utils.RedisConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * <p>description : ShopServiceImpl
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/23 6:55 PM
 */
@Slf4j
@Service
public class ShopServiceImpl implements ShopService {

    @Resource
    private ShopMapper shopMapper;

    @Resource
    private CacheClient cacheClient;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result getById(Long id) {
        // 缓存穿透: 缓存空值
//        Shop shop = cacheClient.queryWithPassThrough("shop:", id, Shop.class,
//                new Function<Long, Shop>() {
//                    @Override
//                    public Shop apply(Long aLong) {
//                        Shop res = shopMapper.selectByPrimaryKey(aLong);
//                        log.info("从 db 中加载数据 {}", res);
//                        return res;
//                    }
//                }, 10L, TimeUnit.SECONDS);
        // 缓存击穿: 互斥锁
//        Shop shop = cacheClient.queryWithMutex(RedisConstants.SHOP_KEY, id, Shop.class,
//                new Function<Long, Shop>() {
//                    @Override
//                    public Shop apply(Long aLong) {
//                        Shop res = shopMapper.selectByPrimaryKey(aLong);
//                        log.info("从 db 中加载数据 {}", res);
//                        try {
//                            // 模拟查询耗时操作
//                            TimeUnit.SECONDS.sleep(5);
//                        } catch (InterruptedException e) {
//                            log.error(e.getMessage(), e);
//                        }
//                        return res;
//                    }
//                }, 10L, TimeUnit.SECONDS);
        // 缓存击穿：逻辑过期时间
        Shop shop = cacheClient.queryWithLogicalExpire(RedisConstants.SHOP_KEY, id, Shop.class,
                new Function<Long, Shop>() {
                    @Override
                    public Shop apply(Long aLong) {
                        Shop res = shopMapper.selectByPrimaryKey(aLong);
                        log.info("从 db 中加载数据 {}", res);
                        return res;
                    }
                }, 10L, TimeUnit.SECONDS);
        return Result.ok(shop);
    }

    @Override
    public Result updateById(Shop shop) {
        // 1. 先更新 db
        int dbResult = shopMapper.updateByPrimaryKey(shop);
        if (dbResult == 0) {
            return Result.fail("更新失败, 数据不存在");
        }
        log.info("更新数据成功 {}", shop);
        // 2. 再删除缓存(缓存击穿-逻辑过期不用删除缓存)
        // stringRedisTemplate.delete(RedisConstants.SHOP_KEY + shop.getId());
        return Result.ok(shop);
    }

}
