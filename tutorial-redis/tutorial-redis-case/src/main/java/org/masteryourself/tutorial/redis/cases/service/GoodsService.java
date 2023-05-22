package org.masteryourself.tutorial.redis.cases.service;

import org.masteryourself.tutorial.redis.cases.domain.Goods;
import org.masteryourself.tutorial.redis.cases.dto.Result;

/**
 * <p>description : GoodsService
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/5 12:46 AM
 */
public interface GoodsService {

    Result create(Goods goods);

    Goods getById(Long voucherId);

    int updateStock(Long goodsId, int stock);

    int updateStockGt0(Long goodsId);
}
