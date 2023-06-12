package org.masteryourself.tutorial.jvm.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>description : 弱引用案例
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/1 11:21 AM
 */
public class WeakReferenceDemo {

    private static final int _4MB = 4 * 1024 * 1204;

    /**
     * 添加 VM 参数 -Xmx20m -XX:+PrintGCDetails -verbose:gc
     */
    public static void main(String[] args) {
        // 引用队列
        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();
        // 添加软引用
        List<WeakReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // 关联引用队列, 当软引用所关联的 byte[] 数组被回收时, 软引用自己会加入到 queue 中
            WeakReference<byte[]> weakReference = new WeakReference<>(new byte[_4MB], queue);
            list.add(weakReference);
            for (WeakReference<byte[]> reference : list) {
                System.out.print(reference.get() + "  ");
            }
            System.out.println();
        }
        // 先清除引用队列中的元素, 如果不移除, 那么 list 中将会存在许多为 null 的软引用对象
        Reference<? extends byte[]> poll = queue.poll();
        while (poll != null) {
            list.remove(poll);
            poll = queue.poll();
        }
        System.out.println("遍历集合中的元素");
        // 看看还剩下多少
        for (WeakReference<byte[]> reference : list) {
            System.out.print(reference.get() + "  ");
        }
        System.out.println();
    }

}
