package org.masteryourself.tutorial.redis.sentinel.config;

import io.lettuce.core.ReadFrom;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;

/**
 * <p>description : RedisConfig
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/9 5:32 PM
 */
@Configuration
public class RedisConfig {

    /**
     * MASTER：从主节点读取
     * MASTER_PREFERRED：优先从 master 节点读取，master 不可用才读取 replica
     * REPLICA：从 slave（replica）节点读取
     * REPLICA _PREFERRED：优先从 slave（replica）节点读取，所有的 slave 都不可用才读取 master
     */
    @Bean
    public LettuceClientConfigurationBuilderCustomizer clientConfigurationBuilderCustomizer() {
        return new LettuceClientConfigurationBuilderCustomizer() {
            @Override
            public void customize(LettuceClientConfiguration.LettuceClientConfigurationBuilder clientConfigurationBuilder) {
                clientConfigurationBuilder.readFrom(ReadFrom.REPLICA_PREFERRED);
            }
        };
    }

}
