package org.masteryourself.tutorial.redis.service;

import org.masteryourself.tutorial.redis.domain.Goods;
import org.masteryourself.tutorial.redis.dto.Result;

/**
 * <p>description : GoodsService
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/5 12:46 AM
 */
public interface GoodsService {

    Result create(Goods goods);

    Goods getById(Long voucherId);

    int updateStock(Goods goods);
}
