package org.masteryourself.tutorial.designpattern.behavioral.state;

/**
 * <p>description : StateTest
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/12 1:39 PM
 */
public class StateTest {

    public static void main(String[] args) {
        Hotel hotel = new Hotel(new RoomState.FreeState());
        hotel.handle();
        hotel.next();
        hotel.handle();
        hotel.next();
        hotel.handle();
        hotel.next();
        hotel.handle();
    }

}
