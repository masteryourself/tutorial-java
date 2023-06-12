package org.masteryourself.tutorial.algorithm.queue.priorityqueue;

/**
 * <p>description : Priority
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/17 20:15
 */
public interface Priority {

    /**
     * 返回对象的优先级, 约定数字越大, 优先级越高
     * @return 优先级
     */
    int priority();

}
