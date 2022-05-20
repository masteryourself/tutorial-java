package org.masteryourself.tutorial.redis.config;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.masteryourself.tutorial.redis.domain.User;
import org.masteryourself.tutorial.redis.utils.UserHolder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>description : token 每次访问时刷新
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/20 12:26 PM
 */
@Component
public class TokenRefreshInterceptor implements HandlerInterceptor {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 从 header 头中获取 authorization
        String authorizationToken = request.getHeader("authorization");
        if (StrUtil.isBlank(authorizationToken)) {
            // 这里需要放行, 因为部分页面不需要登录
            return true;
        }
        // 2. 从 redis 中获取用户信息
        String redisKey = "user:token:" + authorizationToken;
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(redisKey);
        // 3. 判断用户是否存在
        if (userMap.isEmpty()) {
            return true;
        }
        // 4. 将查询到的 hash 数据转为 User
        User user = BeanUtil.fillBeanWithMap(userMap, new User(), false);
        // 5. 存在，保存用户信息到 ThreadLocal
        UserHolder.saveUser(user);
        // 6. 刷新 token 有效期
        stringRedisTemplate.expire(redisKey, 30, TimeUnit.MINUTES);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除用户
        UserHolder.removeUser();
    }

}
