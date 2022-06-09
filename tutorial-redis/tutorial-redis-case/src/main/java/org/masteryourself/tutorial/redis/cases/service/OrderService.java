package org.masteryourself.tutorial.redis.cases.service;

import org.masteryourself.tutorial.redis.cases.dto.Result;

/**
 * <p>description : OrderService
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/5 12:43 AM
 */
public interface OrderService {

    Result create(Long goodsId);
}
