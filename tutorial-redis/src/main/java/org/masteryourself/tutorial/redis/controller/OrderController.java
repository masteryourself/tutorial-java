package org.masteryourself.tutorial.redis.controller;

import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.redis.dto.Result;
import org.masteryourself.tutorial.redis.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>description : OrderController
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/5 12:42 AM
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("create")
    public Result create(Long goodsId) {
        return orderService.create(goodsId);
    }

}
