package org.masteryourself.tutorial.redis.utils;

/**
 * <p>description : RedisConstants
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/22 12:37 PM
 */
public class RedisConstants {

    public static final String USER_CODE_KEY = "user:code";
    public static final Long USER_CODE_TTL = 5L;

    public static final String USER_TOKEN_KEY = "user:token";
    public static final Long USER_TOKEN_TTL = 30L;

}
