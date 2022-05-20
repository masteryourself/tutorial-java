package org.masteryourself.tutorial.redis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * <p>description : mvc 扩展配置
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/20 12:32 PM
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Resource
    private TokenRefreshInterceptor tokenRefreshInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 1. token 刷新的拦截器, 只要访问就需要刷新(前提是登录情况下)
        registry.addInterceptor(tokenRefreshInterceptor)
                .addPathPatterns("/**")
                .order(0);
        // 2. 登录拦截器(部分资源需要排除)
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/**/*.html",
                        "/webjars/**",
                        "/static/**",
                        "/user/sendCode",
                        "/user/login")
                .order(1);
    }
}

