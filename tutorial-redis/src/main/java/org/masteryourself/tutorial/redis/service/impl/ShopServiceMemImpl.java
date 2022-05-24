package org.masteryourself.tutorial.redis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.redis.domain.Shop;
import org.masteryourself.tutorial.redis.dto.Result;
import org.masteryourself.tutorial.redis.service.ShopService;
import org.masteryourself.tutorial.redis.utils.CacheClient;
import org.masteryourself.tutorial.redis.utils.RedisConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * <p>description : ShopServiceMemImpl
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/23 6:55 PM
 */
@Slf4j
@Service
public class ShopServiceMemImpl implements ShopService {

    private static final List<Shop> SHOP_DB = new CopyOnWriteArrayList<>();

    static {
        SHOP_DB.add(new Shop(1L, "羊肉铺", "专门卖羊肉"));
        SHOP_DB.add(new Shop(2L, "猪肉铺", "专门卖猪肉"));
        SHOP_DB.add(new Shop(3L, "五金杂货店", "五金产品相关"));
    }

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
//                        Shop res = SHOP_DB.stream().filter(shop -> shop.getId().equals(id)).findFirst().orElse(null);
//                        log.info("从 db 中加载数据 {}", res);
//                        return res;
//                    }
//                }, 10L, TimeUnit.SECONDS);
        // 缓存击穿: 互斥锁
//        Shop shop = cacheClient.queryWithMutex(RedisConstants.SHOP_KEY, id, Shop.class,
//                new Function<Long, Shop>() {
//                    @Override
//                    public Shop apply(Long aLong) {
//                        Shop res = SHOP_DB.stream().filter(shop -> shop.getId().equals(id)).findFirst().orElse(null);
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
                        Shop res = SHOP_DB.stream().filter(shop -> shop.getId().equals(id)).findFirst().orElse(null);
                        log.info("从 db 中加载数据 {}", res);
                        return res;
                    }
                }, 10L, TimeUnit.SECONDS);
        return Result.ok(shop);
    }

    @Override
    public Result updateById(Shop update) {
        Long updateId = update.getId();
        // 1. 先更新 db
        Shop query = SHOP_DB.stream().filter(shop -> shop.getId().equals(updateId)).findFirst().orElse(null);
        if (query == null) {
            return Result.fail("更新失败, 数据不存在");
        }
        query.setName(update.getName());
        log.info("更新数据成功 {}", query);
        // 2. 再删除缓存(缓存击穿-逻辑过期不用删除缓存)
        // stringRedisTemplate.delete(RedisConstants.SHOP_KEY + updateId);
        return Result.ok(query);
    }

}
