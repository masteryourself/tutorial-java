package org.masteryourself.tutorial.juc.completablefuture;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>description : CalcPriceDemo
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/22 2:36 PM
 */
public class CalcPriceDemo {

    private static final List<Mall> MALL_LIST = Arrays.asList(new Mall("TMALL"), new Mall("PDD"), new Mall("JD"));

    public static void main(String[] args) {
        CalcPriceDemo demo = new CalcPriceDemo();
        demo.demo1();
        demo.demo2();
    }

    public void demo1() {
        long start = System.currentTimeMillis();
        List<String> res1 = MALL_LIST.stream().map(mall -> String.format("%s price is %.2f", mall.getName(), mall.calcPrice()))
                .collect(Collectors.toList());
        System.out.println("方法一耗时:" + (System.currentTimeMillis() - start) + "s");
        System.out.println(res1);
    }

    public void demo2() {
        long start = System.currentTimeMillis();
        List<String> res2 = MALL_LIST.stream().map(mall ->
                        CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", mall.getName(), mall.calcPrice())))
                .collect(Collectors.toList())
                .stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println("方法二耗时:" + (System.currentTimeMillis() - start) + "s");
        System.out.println(res2);
    }

    @AllArgsConstructor
    @Getter
    public static class Mall {

        private String name;

        private Double calcPrice() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return ThreadLocalRandom.current().nextDouble(90, 100);
        }
    }

}
