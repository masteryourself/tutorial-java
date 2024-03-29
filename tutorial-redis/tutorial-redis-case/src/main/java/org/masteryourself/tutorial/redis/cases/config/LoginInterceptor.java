package org.masteryourself.tutorial.redis.cases.config;

import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.redis.cases.utils.UserHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>description : 登录拦截器
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/20 12:26 PM
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 必须要求强制登录(只对部分资源)
        if (UserHolder.getUser() == null) {
            log.warn("用户未登录, 拦截请求 {}", request.getRequestURI());
            response.setStatus(401);
            return false;
        }
        return true;
    }

}
