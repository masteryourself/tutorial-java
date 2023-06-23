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
                // 使用 LengthFieldBasedFrameDecoder 解决粘包和半包问题
                // 使用自定义协议可以解决粘包问题(因为读取报文长度固定了), 但是无法解决半包问题
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

        // 正常测试
        // channel.writeInbound(buf);

        // 半包测试, 这里发送两条信息
        ByteBuf buf1 = buf.slice(0, 100);
        ByteBuf buf2 = buf.slice(100, buf.readableBytes() - 100);
        // 防止调用完 writeInbound 后被释放
        buf1.retain();
        channel.writeInbound(buf1);
        channel.writeInbound(buf2);
    }

}
