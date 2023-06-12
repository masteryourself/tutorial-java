package org.masteryourself.tutorial.designpattern.structual.bridge;

/**
 * <p>description : BridgeTest
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/27 2:54 PM
 */
public class BridgeTest {

    public static void main(String[] args) {
        Phone phone = new Phone.IPhone(new Sale.OfflineSale("线上渠道", "6666$"));
        phone.printInfo();
    }

}
