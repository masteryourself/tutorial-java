package org.masteryourself.tutorial.redis.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>description : RedisData
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/22 9:12 PM
 */
@Data
public class RedisData {

    private LocalDateTime expireTime;

    private Object data;

}
