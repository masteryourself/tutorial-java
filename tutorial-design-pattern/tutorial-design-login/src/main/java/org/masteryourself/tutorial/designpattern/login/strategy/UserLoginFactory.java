package org.masteryourself.tutorial.designpattern.login.strategy;

import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.designpattern.login.anno.LoginStrategy;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 操作策略的上下文环境类 工具类
 * 将策略整合起来 方便管理
 */
@Slf4j
@Component
public class UserLoginFactory implements ApplicationContextAware {

    private static final Map<String, UserGranter> granterPool = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String[] beanNames = applicationContext.getBeanNamesForType(UserGranter.class);
        for (String beanName : beanNames) {
            UserGranter userGranter = applicationContext.getBean(beanName, UserGranter.class);
            LoginStrategy loginStrategy = userGranter.getClass().getAnnotation(LoginStrategy.class);
            granterPool.put(loginStrategy.name(), userGranter);
            log.info("granterPool 初始化 {} -> {}", loginStrategy.name(), beanName);
        }
    }

    /**
     * 对外提供获取具体策略
     *
     * @param grantType 用户的登录方式，需要跟配置文件中匹配
     * @return 具体策略
     */
    public UserGranter getGranter(String grantType) {
        return granterPool.get(grantType);
    }

}
