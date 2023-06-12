package org.masteryourself.tutorial.algorithm.queue.priorityqueue;

/**
 * <p>description : Entry
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/17 20:43
 */
public class Entry implements Priority {

    public String name;
    public int priority;

    public Entry(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public int priority() {
        return this.priority;
    }

}
