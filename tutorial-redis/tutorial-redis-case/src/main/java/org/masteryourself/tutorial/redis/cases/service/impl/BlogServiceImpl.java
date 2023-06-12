package org.masteryourself.tutorial.redis.cases.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import org.masteryourself.tutorial.redis.cases.Blog;
import org.masteryourself.tutorial.redis.cases.domain.User;
import org.masteryourself.tutorial.redis.cases.dto.Result;
import org.masteryourself.tutorial.redis.cases.mapper.BlogMapper;
import org.masteryourself.tutorial.redis.cases.service.BlogService;
import org.masteryourself.tutorial.redis.cases.service.UserService;
import org.masteryourself.tutorial.redis.cases.utils.UserHolder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>description : BlogServiceImpl
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/7 6:52 PM
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result create(Blog blog) {
        // 创建博客
        return Result.ok(blogMapper.insertSelective(blog));
    }

    @Override
    public Result like(Long blogId) {
        // 1. 获取登录用户 id
        User user = UserHolder.getUser();
        if (user == null) {
            // 说明当前用户未登录
            return Result.ok();
        }
        Long userId = user.getId();
        // 2. 判断当前用户是否已经点过赞
        String key = "blog:like:" + blogId;
        Double score = stringRedisTemplate.opsForZSet().score(key, userId.toString());
        if (score == null) {
            // 如果未点赞, 则可以点赞
            int likedRes = blogMapper.liked(blogId);
            if (likedRes == 1) {
                // 添加到 sortedSet 集合中
                stringRedisTemplate.opsForZSet().add(key, userId.toString(), System.currentTimeMillis());
            }
        } else {
            // 如果已经点过赞, 则取消赞
            int unlikedRes = blogMapper.unliked(blogId);
            if (unlikedRes == 1) {
                // 从 sortedSet 集合中移除
                stringRedisTemplate.opsForZSet().remove(key, userId.toString());
            }
        }
        return Result.ok();
    }

    public Result list(Long blogId) {
        // 1. 查询最早点赞 top3
        String key = "blog:like:" + blogId;
        Set<String> top3 = stringRedisTemplate.opsForZSet().range(key, 0, 2);
        if (CollectionUtil.isEmpty(top3)) {
            return Result.ok();
        }
        // 2. 解析出用户 id
        List<Long> userIds = top3.stream().map(Long::valueOf).collect(Collectors.toList());
        List<User> userList = userService.findByIds(userIds);
        return Result.ok(userList);
    }

}
