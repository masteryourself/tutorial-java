package org.masteryourself.tutorial.redis.service.impl;

import org.masteryourself.tutorial.redis.domain.Goods;
import org.masteryourself.tutorial.redis.dto.Result;
import org.masteryourself.tutorial.redis.mapper.GoodsMapper;
import org.masteryourself.tutorial.redis.service.GoodsService;
import org.masteryourself.tutorial.redis.utils.RedisConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>description : GoodsServiceImpl
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/5 12:46 AM
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result create(Goods goods) {
        int res = goodsMapper.insertSelective(goods);
        if (res <= 0) {
            return Result.fail("保存商品失败");
        }
        // 把商品库存保存到 redis 中
        stringRedisTemplate.opsForValue().set(
                RedisConstants.SEC_KILL_STOCK_KEY + goods.getId(),
                String.valueOf(goods.getStock()));
        return Result.ok(res);
    }

    @Override
    public Goods getById(Long voucherId) {
        return goodsMapper.selectByPrimaryKey(voucherId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateStock(Long goodsId, int stock) {
        return goodsMapper.updateStock(goodsId, stock);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateStockGt0(Long goodsId) {
        return goodsMapper.updateStockGt0(goodsId);
    }
}
