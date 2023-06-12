package org.masteryourself.tutorial.netty.protocol.customize;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;

/**
 * <p>description : CustomizeProtocolTest
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/15 8:28 PM
 */
public class CustomizeProtocolTest {

    public static void main(String[] args) throws Exception {
        EmbeddedChannel channel = new EmbeddedChannel(
                new LoggingHandler(),
                new LengthFieldBasedFrameDecoder(1024, 12, 4, 0, 0),
                new MessageCodec()
        );
        Message message = Message.builder()
                .messageType((byte) 1)
                .sequenceId(10001)
                .username("zhangsan")
                .password("123456")
                .build();
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null, message, buf);
        channel.writeInbound(buf);
    }

}
