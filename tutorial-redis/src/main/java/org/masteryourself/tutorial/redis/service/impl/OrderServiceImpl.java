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

import javax.annotation.Resource;
import java.time.LocalDateTime;

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
        // 3. 扣减库存
        int stockOpRes = goodsService.updateStock(goods);
        if (stockOpRes <= 0) {
            return Result.fail("扣减库存失败");
        }
        // 4. 生成订单
        Order order = new Order();
        order.setId(redisIdGenerate.nextId("order"));
        Long userId = UserHolder.getUser().getId();
        order.setUserId(userId);
        order.setGoodsId(goodsId);
        return Result.ok(orderMapper.insertSelective(order));
    }

}
