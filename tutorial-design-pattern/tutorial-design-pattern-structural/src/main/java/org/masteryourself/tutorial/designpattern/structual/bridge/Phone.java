package org.masteryourself.tutorial.designpattern.structual.bridge;

/**
 * <p>description : Phone
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/27 2:45 PM
 */
public abstract class Phone {

    private String name;

    // 桥接层, 一部分实现分离出去, 给外部独立扩展渠道
    private Sale sale;

    public Phone(String name, Sale sale) {
        this.name = name;
        this.sale = sale;
    }

    void printInfo() {
        System.out.println(this.name + "售卖信息：" + sale);
    }

    public static class IPhone extends Phone {

        public IPhone(Sale sale) {
            super("苹果手机", sale);
        }
    }

}
