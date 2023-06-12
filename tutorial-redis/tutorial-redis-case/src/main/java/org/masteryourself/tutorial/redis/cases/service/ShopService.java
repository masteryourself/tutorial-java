package org.masteryourself.tutorial.redis.cases.service;

import org.masteryourself.tutorial.redis.cases.domain.Shop;
import org.masteryourself.tutorial.redis.cases.dto.Result;

/**
 * <p>description : ShopService
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/23 6:55 PM
 */
public interface ShopService {

    Result getById(Long id);

    Result updateById(Shop update);

}
