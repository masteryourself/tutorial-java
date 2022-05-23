package org.masteryourself.tutorial.redis.controller;

import cn.hutool.core.util.RandomUtil;
import org.masteryourself.tutorial.redis.domain.Shop;
import org.masteryourself.tutorial.redis.dto.Result;
import org.masteryourself.tutorial.redis.service.ShopService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>description : ShopController
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/23 6:54 PM
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Resource
    private ShopService shopService;

    @GetMapping("query")
    public Result query(@RequestParam("id") Long id) {
        return shopService.getById(id);
    }

    @GetMapping("update")
    public Result update(@RequestParam("id") Long id) {
        Shop update = new Shop();
        update.setId(id);
        update.setName(RandomUtil.randomString(6));
        return shopService.updateById(update);
    }

}
