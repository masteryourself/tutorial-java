package org.masteryourself.tutorial.redis.cases.utils;

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

    /**
     * 用户验证码
     */
    public static final String USER_CODE_KEY = "user:code:";
    public static final Long USER_CODE_TTL = 5L;

    /**
     * 用户 token
     */
    public static final String USER_TOKEN_KEY = "user:token:";
    public static final Long USER_TOKEN_TTL = 30L;

    /**
     * 用户签到
     */
    public static final String USER_SIGN_KEY = "user:sign:";

    /**
     * 缓存穿透空值
     */
    public static final String CACHE_PASS_THROUGH_INVALID_FLAG = "empty";
    public static final Long CACHE_PASS_THROUGH_INVALID_TTL = 10L;

    /**
     * 互斥锁前缀
     */
    public static final String LOCK_KEY = "lock:";

    /**
     * 商品缓存前缀
     */
    public static final String SHOP_KEY = "shop:";

    /**
     * 分布式 ID 前缀
     */
    public static final String ID_GEN_KEY = "idgen:";

    /**
     * 秒杀商品库存
     */
    public static final String SEC_KILL_STOCK_KEY = "seckill:stock:";

}
