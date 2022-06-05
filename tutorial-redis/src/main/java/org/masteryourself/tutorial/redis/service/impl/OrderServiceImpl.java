package org.masteryourself.tutorial.redis.service.impl;

import org.masteryourself.tutorial.redis.domain.Order;
import org.masteryourself.tutorial.redis.domain.Goods;
import org.masteryourself.tutorial.redis.dto.Result;
import org.masteryourself.tutorial.redis.mapper.OrderMapper;
import org.masteryourself.tutorial.redis.service.OrderService;
import org.masteryourself.tutorial.redis.service.GoodsService;
import org.masteryourself.tutorial.redis.utils.RedisIdGenerate;
import org.masteryourself.tutorial.redis.utils.UserHolder;
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
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private GoodsService goodsService;

    @Resource
    private RedisLock redisLock;

    @Resource
    private RedisIdGenerate redisIdGenerate;

    @Override
    @Transactional(rollbackFor = Exception.class)
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
        return createOrder(goods);
    }

    private Result createOrder(Goods goods) {
        Long userId = UserHolder.getUser().getId();
        Long goodsId = goods.getId();
        int orderOpRes;
        // 锁粒度是 goodsId + userId
        String name = "order:create:" + goodsId + "_" + userId;
        // 1. 判断是否获取到锁, 每个人只能购买一次
        if (!redisLock.tryLock(name, 30, TimeUnit.SECONDS)) {
            return Result.fail("活动太火爆, 请稍等");
        }
        try {
            // 1. 校验一人一单
            Example example = new Example(Order.class);
            example.createCriteria().andEqualTo("userId", userId).andEqualTo("goodsId", goodsId);
            List<Order> orderList = orderMapper.selectByExample(example);
            if (orderList.size() > 0) {
                return Result.fail("超出购买限制");
            }
            // 2. 扣减库存
            int stockOpRes = goodsService.updateStock(goods);
            if (stockOpRes <= 0) {
                return Result.fail("扣减库存失败");
            }
            // 3. 生成订单
            Order order = new Order();
            order.setId(redisIdGenerate.nextId("order"));
            order.setUserId(userId);
            order.setGoodsId(goodsId);
            orderOpRes = orderMapper.insertSelective(order);
            if (orderOpRes <= 0) {
                return Result.fail("订单保存失败");
            }
        } finally {
            // 4. 释放锁
            redisLock.unlock(name);
        }
        return Result.ok(orderOpRes);
    }

}
