package org.masteryourself.tutorial.redis.cases.controller;

import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.redis.cases.Blog;
import org.masteryourself.tutorial.redis.cases.dto.Result;
import org.masteryourself.tutorial.redis.cases.service.BlogService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>description : BlogController
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/7 6:50 PM
 */
@Slf4j
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    @PostMapping("create")
    public Result create(@RequestBody Blog blog) {
        return blogService.create(blog);
    }

    @PostMapping("like")
    public Result like(Long blogId) {
        return blogService.like(blogId);
    }

    @PostMapping("list")
    public Result list(Long blogId) {
        return blogService.list(blogId);
    }

}
