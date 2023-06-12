package org.masteryourself.tutorial.designpattern.creatation.builder;

/**
 * <p>description : PhoneBuilderTest
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/2/26 6:44 PM
 */
public class PhoneBuilderTest {

    public static void main(String[] args) {
        Phone phone = new PhoneBuilder.XiaomiPhoneBuilder()
                .cpu("骁龙888")
                .mem("16G")
                .disk("512G")
                .build();
        System.out.println(phone);
    }

}
