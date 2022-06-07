package org.masteryourself.tutorial.redis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.masteryourself.tutorial.redis.domain.Goods;
import tk.mybatis.mapper.common.Mapper;

/**
 * <p>description : VoucherMapper
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/5 12:58 AM
 */
public interface VoucherMapper extends Mapper<Goods> {

    /**
     * 优化 1: 基于库存的乐观锁改良, 即把 stock 字段当成 version 作为版本控制
     * 如果这里每次都用乐观锁校验, 会有大量的重试才可以执行成功, 否则大量并发请求都会失败
     * 乐观锁: 提供一个 version 字段, 每次更新时需要校验当前的 version 是不是之前查询出来的 version
     */
    @Update("update goods set stock = stock-1 where id = #{goodsId} and stock = #{stock}")
    int updateStock(@Param("goodsId") Long goodsId, @Param("stock") int stock);

    /**
     * 优化 2: 基于库存的乐观锁改良, 只要满足 stock > 0 就可以扣减库存, 可以减少无畏重试
     */
    @Update("update goods set stock = stock-1 where id = #{goodsId} and stock > 0")
    int updateStockGt0(@Param("goodsId") Long goodsId);
}
