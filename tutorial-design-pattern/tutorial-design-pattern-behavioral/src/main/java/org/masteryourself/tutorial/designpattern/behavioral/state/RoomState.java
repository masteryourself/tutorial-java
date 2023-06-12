package org.masteryourself.tutorial.designpattern.behavioral.state;

/**
 * <p>description : RoomState
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/12 1:30 PM
 */
public interface RoomState {

    void handle();

    RoomState next();

    class BookState implements RoomState {

        @Override
        public void handle() {
            System.out.println("房间已经预定了");
        }

        @Override
        public RoomState next() {
            return new CheckState();
        }
    }

    class CheckState implements RoomState {

        @Override
        public void handle() {
            System.out.println("房间已入住");
        }

        @Override
        public RoomState next() {
            return new FreeState();
        }
    }

    class FreeState implements RoomState {

        @Override
        public void handle() {
            System.out.println("房间空闲");
        }

        @Override
        public RoomState next() {
            return new BookState();
        }
    }

}
