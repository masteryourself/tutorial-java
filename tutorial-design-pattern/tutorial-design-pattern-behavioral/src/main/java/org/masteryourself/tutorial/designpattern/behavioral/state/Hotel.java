package org.masteryourself.tutorial.designpattern.behavioral.state;

/**
 * <p>description : Hotel
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/12 1:33 PM
 */
public class Hotel {

    private RoomState roomState;

    public Hotel(RoomState roomState) {
        this.roomState = roomState;
    }

    public void handle() {
        roomState.handle();
    }

    public void next() {
        this.roomState = roomState.next();
    }

}
