package org.masteryourself.tutorial.redis.service;

import org.masteryourself.tutorial.redis.domain.Shop;
import org.masteryourself.tutorial.redis.dto.Result;

/**
 * <p>description : ShopService
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/23 6:55 PM
 */
public interface ShopService {

    Result getById(Long id);

    Result updateById(Shop update);

}
