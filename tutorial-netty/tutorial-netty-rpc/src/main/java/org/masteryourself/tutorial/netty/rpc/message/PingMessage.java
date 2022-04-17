package org.masteryourself.tutorial.netty.rpc.message;

/**
 * <p>description : PingMessage
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/17 1:31 PM
 */
public class PingMessage extends Message {

    @Override
    public int getMessageType() {
        return PingMessage;
    }

}

