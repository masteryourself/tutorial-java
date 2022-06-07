package org.masteryourself.tutorial.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>description : RedissonConfig
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/6 12:00 AM
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        // 也可以使用 useClusterServers() 配置集群地址
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379")
                .setPassword("123321");
        return Redisson.create(config);
    }

}
