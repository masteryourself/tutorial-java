package org.masteryourself.tutorial.concurrent.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>description : AtomicAccountDemo
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/28 15:44
 */
@Slf4j
public class AtomicAccountDemo {

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
        log.info("剩余余额是 {}", account.getBalance());
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
