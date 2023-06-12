package org.masteryourself.tutorial.designpattern.structual.bridge;

import lombok.ToString;

/**
 * <p>description : Sale
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/27 2:48 PM
 */
@ToString
public abstract class Sale {

    private String type;

    private String price;

    public Sale(String type, String price) {
        this.type = type;
        this.price = price;
    }

    public static class OnlineSale extends Sale {

        public OnlineSale(String type, String price) {
            super(type, price);
        }

    }

    public static class OfflineSale extends Sale {

        public OfflineSale(String type, String price) {
            super(type, price);
        }

    }

}
