package org.masteryourself.tutorial.redis.cases.controller;

import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.redis.cases.dto.Result;
import org.masteryourself.tutorial.redis.cases.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>description : OrderController
 *
 * <p>blog : https://www.yuque.com/masteryoursef
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
    private OrderService orderServiceImpl;

    @Resource
    private OrderService orderAsyncServiceImpl;

    @PostMapping("create")
    public Result create(Long goodsId) {
        return orderServiceImpl.create(goodsId);
    }

    @PostMapping("create_async")
    public Result createAsync(Long goodsId) {
        return orderAsyncServiceImpl.create(goodsId);
    }

}
