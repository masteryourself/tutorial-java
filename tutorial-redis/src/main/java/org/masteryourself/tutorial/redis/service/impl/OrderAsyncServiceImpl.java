package org.masteryourself.tutorial.redis.service.impl;

import cn.hutool.json.JSONUtil;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.redis.domain.Goods;
import org.masteryourself.tutorial.redis.domain.Order;
import org.masteryourself.tutorial.redis.dto.Result;
import org.masteryourself.tutorial.redis.dto.TopicOrderData;
import org.masteryourself.tutorial.redis.mapper.OrderMapper;
import org.masteryourself.tutorial.redis.service.GoodsService;
import org.masteryourself.tutorial.redis.service.OrderService;
import org.masteryourself.tutorial.redis.utils.RedisIdGenerate;
import org.masteryourself.tutorial.redis.utils.UserHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>description : OrderAsyncServiceImpl
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/5 12:44 AM
 */
@Slf4j
@Service
public class OrderAsyncServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private GoodsService goodsService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisIdGenerate redisIdGenerate;

    private static final DefaultRedisScript<Long> SEC_KILL_SCRIPT;

    static {
        SEC_KILL_SCRIPT = new DefaultRedisScript<>();
        SEC_KILL_SCRIPT.setLocation(new ClassPathResource("lua/seckill.lua"));
        SEC_KILL_SCRIPT.setResultType(Long.class);
    }

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
        // 2. 创建订单
        return this.createAsyncOrder(goods);
    }

    /**
     * 异步下单
     */
    private Result createAsyncOrder(Goods goods) {
        Long userId = UserHolder.getUser().getId();
        Long goodsId = goods.getId();
        long orderId = redisIdGenerate.nextId("order");
        Long res = stringRedisTemplate.execute(SEC_KILL_SCRIPT, Collections.emptyList(),
                String.valueOf(goodsId), String.valueOf(userId), String.valueOf(orderId));
        if (res == null) {
            return Result.fail("下单失败");
        }
        int result = res.intValue();
        if (result == 1) {
            return Result.fail("商品库存不足");
        }
        if (result == 2) {
            return Result.fail("超出购买限制");
        }
        return Result.ok("下单成功");
    }

    @PostConstruct
    public void dealTopicOrder() {
        Executors.newSingleThreadExecutor(r -> new Thread(r, "deal-topic-order"))
                .execute(() -> {
                    while (true) {
                        // 从 topic:order 中获取订单数据, 完成下单操作
                        String result = stringRedisTemplate.opsForList().rightPop("topic:order", 3, TimeUnit.SECONDS);
                        if (StringUtil.isBlank(result)) {
                            continue;
                        }
                        TopicOrderData topicOrderData = JSONUtil.toBean(result, TopicOrderData.class);
                        Long userId = topicOrderData.getUserId();
                        Long goodsId = topicOrderData.getGoodsId();
                        // 1. 校验一人一单
                        Example example = new Example(Order.class);
                        example.createCriteria().andEqualTo("userId", userId).andEqualTo("goodsId", goodsId);
                        List<Order> orderList = orderMapper.selectByExample(example);
                        if (orderList.size() > 0) {
                            log.error("超出购买限制");
                            return;
                        }
                        // 2. 扣减库存
                        int stockOpRes = goodsService.updateStockGt0(goodsId);
                        if (stockOpRes <= 0) {
                            log.error("商品库存扣减失败");
                            return;
                        }
                        // 3. 生成订单
                        Order order = new Order();
                        order.setId(redisIdGenerate.nextId("order"));
                        order.setUserId(userId);
                        order.setGoodsId(goodsId);
                        int orderOpRes = orderMapper.insertSelective(order);
                        if (orderOpRes <= 0) {
                            log.error("订单生成失败");
                            return;
                        }
                        log.info("异步创单完成 {}", order);
                    }
                });
    }

}
