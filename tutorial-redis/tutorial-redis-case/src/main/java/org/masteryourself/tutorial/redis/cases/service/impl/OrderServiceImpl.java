package org.masteryourself.tutorial.redis.cases.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.redis.cases.domain.Goods;
import org.masteryourself.tutorial.redis.cases.domain.Order;
import org.masteryourself.tutorial.redis.cases.dto.Result;
import org.masteryourself.tutorial.redis.cases.mapper.OrderMapper;
import org.masteryourself.tutorial.redis.cases.service.GoodsService;
import org.masteryourself.tutorial.redis.cases.service.OrderService;
import org.masteryourself.tutorial.redis.cases.utils.RedisIdGenerate;
import org.masteryourself.tutorial.redis.cases.utils.RedisLock;
import org.masteryourself.tutorial.redis.cases.utils.UserHolder;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopContext;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>description : OrderServiceImpl
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/5 12:44 AM
 */
@Slf4j
@Service
@Primary
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private GoodsService goodsService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private RedisIdGenerate redisIdGenerate;

    @Override
    public Result create(Long goodsId) {
        // 1. 校验优惠券开始时间和结束时间
        Goods goods = goodsService.getById(goodsId);
        if (goods == null) {
            return Result.fail("优惠券不存在");
        }
        LocalDateTime now = LocalDateTime.now();
        if (goods.getBeginTime().isAfter(now)) {
            return Result.fail("秒杀尚未开始");
        }
        if (goods.getEndTime().isBefore(now)) {
            return Result.fail("秒杀已经结束");
        }
        // 2. 校验库存
        if (goods.getStock() <= 0) {
            return Result.fail("商品库存不足");
        }
        // 3. 创建订单
        // return ((OrderServiceImpl) AopContext.currentProxy()).createOrderWithSetNx(goodsId, goods.getStock());
        return ((OrderServiceImpl) AopContext.currentProxy()).createOrderWithRedisson(goodsId);
    }

    /**
     * 使用 redisson 实现
     */
    @Transactional(rollbackFor = Exception.class)
    public Result createOrderWithRedisson(Long goodsId) {
        Long userId = UserHolder.getUser().getId();
        int orderOpRes;
        // 锁粒度是 goodsId + userId
        String name = "order:create:" + goodsId + "_" + userId;
        // 1. 判断是否获取到锁, 每个人只能购买一次
        RLock lock = redissonClient.getLock(name);
        try {
            // 参数1: 获取锁的等待时间, 不为 -1 则会触发重试机制, 这里最多等待 10s(如果不传则默认值为-1, 不能触发重试机制)
            // 参数2: 默认释放锁的超时时间, 上锁之后 30s 会自动解锁(如果不传则默认值为-1, 会触发看门狗机制)
            // 参数3: 时间单位
            if (!lock.tryLock(10, 30, TimeUnit.SECONDS)) {
                return Result.fail("活动太火爆, 请稍等");
            }
        } catch (InterruptedException e) {
            log.error("获取锁失败", e);
        }
        try {
            // 2. 校验一人一单
            Example example = new Example(Order.class);
            example.createCriteria().andEqualTo("userId", userId).andEqualTo("goodsId", goodsId);
            List<Order> orderList = orderMapper.selectByExample(example);
            if (orderList.size() > 0) {
                return Result.fail("超出购买限制");
            }
            // 3. 扣减库存
            int stockOpRes = goodsService.updateStockGt0(goodsId);
            if (stockOpRes <= 0) {
                return Result.fail("扣减库存失败");
            }
            // 4. 生成订单
            Order order = new Order();
            order.setId(redisIdGenerate.nextId("order"));
            order.setUserId(userId);
            order.setGoodsId(goodsId);
            orderOpRes = orderMapper.insertSelective(order);
            if (orderOpRes <= 0) {
                return Result.fail("订单保存失败");
            }
        } finally {
            // 5. 释放锁
            lock.unlock();
        }
        return Result.ok("下单成功");
    }

    /**
     * 使用 setnx 实现分布式锁
     */
    @Transactional(rollbackFor = Exception.class)
    public Result createOrderWithSetNx(Long goodsId, int stock) {
        Long userId = UserHolder.getUser().getId();
        int orderOpRes;
        // 锁粒度是 goodsId + userId
        String name = "order:create:" + goodsId + "_" + userId;
        RedisLock lock = new RedisLock(name, stringRedisTemplate);
        // 1. 判断是否获取到锁, 每个人只能购买一次
        if (!lock.tryLock(30, TimeUnit.SECONDS)) {
            return Result.fail("活动太火爆, 请稍等");
        }
        try {
            // 2. 校验一人一单
            Example example = new Example(Order.class);
            example.createCriteria().andEqualTo("userId", userId).andEqualTo("goodsId", goodsId);
            List<Order> orderList = orderMapper.selectByExample(example);
            if (orderList.size() > 0) {
                return Result.fail("超出购买限制");
            }
            // 3. 扣减库存
            int stockOpRes = goodsService.updateStock(goodsId, stock);
            if (stockOpRes <= 0) {
                return Result.fail("扣减库存失败");
            }
            // 4. 生成订单
            Order order = new Order();
            order.setId(redisIdGenerate.nextId("order"));
            order.setUserId(userId);
            order.setGoodsId(goodsId);
            orderOpRes = orderMapper.insertSelective(order);
            if (orderOpRes <= 0) {
                return Result.fail("订单保存失败");
            }
        } finally {
            // 5. 释放锁
            lock.unlock();
        }
        return Result.ok("下单成功");
    }

}
