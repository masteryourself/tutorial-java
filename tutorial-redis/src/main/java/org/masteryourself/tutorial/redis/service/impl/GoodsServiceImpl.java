package org.masteryourself.tutorial.redis.service.impl;

import org.masteryourself.tutorial.redis.domain.Goods;
import org.masteryourself.tutorial.redis.dto.Result;
import org.masteryourself.tutorial.redis.mapper.VoucherMapper;
import org.masteryourself.tutorial.redis.service.GoodsService;
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
    private VoucherMapper voucherMapper;

    @Override
    public Result create(Goods goods) {
        return Result.ok(voucherMapper.insertSelective(goods));
    }

    @Override
    public Goods getById(Long voucherId) {
        return voucherMapper.selectByPrimaryKey(voucherId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(Goods goods) {
        return voucherMapper.updateByPrimaryKey(goods);
    }
}
