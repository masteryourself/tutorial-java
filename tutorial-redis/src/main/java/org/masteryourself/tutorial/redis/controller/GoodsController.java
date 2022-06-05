package org.masteryourself.tutorial.redis.controller;

import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.redis.domain.Goods;
import org.masteryourself.tutorial.redis.dto.Result;
import org.masteryourself.tutorial.redis.service.GoodsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>description : GoodsController
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/5 12:47 AM
 */
@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @PostMapping("create")
    public Result create(Goods goods) {
        return goodsService.create(goods);
    }

}
