package org.masteryourself.tutorial.redis.cases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * <p>description : RedisCaseApplication
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/19 6:59 PM
 */
@SpringBootApplication
@MapperScan("org.masteryourself.tutorial.redis.cases.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class RedisCaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisCaseApplication.class, args);
    }

}
