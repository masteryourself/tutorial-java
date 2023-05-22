package org.masteryourself.tutorial.concurrent.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>description : UnsafeAccountDemo
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/9/12 18:33
 */
public class UnsafeAccountDemo {

    public static void main(String[] args) {
        UnsafeAccount account = new UnsafeAccount(10000);
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(() -> {
                account.withdraw(10);
            }));
        }
        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(account.getBalance());
    }

    public interface Account {

        Integer getBalance();

        void withdraw(Integer amount);
    }

    public static class UnsafeAccount implements Account {

        private final int data;
        static final Unsafe unsafe;
        static final long DATA_OFFSET;

        static {
            unsafe = UnsafeAccessor.getUnsafe();
            Field dataField = null;
            try {
                dataField = UnsafeAccount.class.getDeclaredField("data");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            DATA_OFFSET = unsafe.objectFieldOffset(dataField);
        }

        public UnsafeAccount(int balance) {
            this.data = balance;
        }

        @Override
        public Integer getBalance() {
            return this.data;
        }

        @Override
        public void withdraw(Integer amount) {
            while (true) {
                // cas 操作可能失败, 需要重试
                if (unsafe.compareAndSwapInt(this, DATA_OFFSET, data, data - amount)) {
                    break;
                }
            }
        }
    }

}
