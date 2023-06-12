package org.masteryourself.tutorial.concurrent.atomic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>description : AtomicReferenceDemo
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/9/3 15:45
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        Account account = new CasAccount(new BigDecimal("10000"));
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(() -> {
                account.withdraw(BigDecimal.TEN);
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

        BigDecimal getBalance();

        void withdraw(BigDecimal amount);
    }

    public static class CasAccount implements Account {

        private AtomicReference<BigDecimal> balance;

        public CasAccount(BigDecimal balance) {
            this.balance = new AtomicReference<>(balance);
        }

        @Override
        public BigDecimal getBalance() {
            return balance.get();
        }

        @Override
        public void withdraw(BigDecimal amount) {
            while (true) {
                BigDecimal prev = this.balance.get();
                BigDecimal next = prev.subtract(amount);
                // 如果 cas 操作成功, 跳出循环
                if (this.balance.compareAndSet(prev, next)) {
                    break;
                }
            }
        }
    }

}
