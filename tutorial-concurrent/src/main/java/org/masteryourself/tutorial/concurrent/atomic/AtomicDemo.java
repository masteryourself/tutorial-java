package org.masteryourself.tutorial.concurrent.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>description : AtomicDemo
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/9/3 14:27
 */
public class AtomicDemo {

    public static void main(String[] args) {
        Account account = new CasAccount(10000);
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

    public static class CasAccount implements Account {

        private AtomicInteger balance;

        public CasAccount(Integer balance) {
            this.balance = new AtomicInteger(balance);
        }

        @Override
        public Integer getBalance() {
            return balance.get();
        }

        @Override
        public void withdraw(Integer amount) {
            while (true) {
                int prev = this.balance.get();
                int next = prev - amount;
                // 如果 cas 操作成功, 跳出循环
                if (this.balance.compareAndSet(prev, next)) {
                    break;
                }
            }
        }
    }

}
