package org.masteryourself.tutorial.redis.service;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : Lock
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/5 9:22 PM
 */
public interface Lock {

    boolean tryLock(String name, long time, TimeUnit unit);

    void unlock(String name);

}
