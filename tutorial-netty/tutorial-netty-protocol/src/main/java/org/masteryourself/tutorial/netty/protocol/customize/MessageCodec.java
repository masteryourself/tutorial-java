package org.masteryourself.tutorial.netty.protocol.customize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * <p>description : MessageCodec
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/15 8:13 PM
 */
@Slf4j
public class MessageCodec extends ByteToMessageCodec<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        // 1. 4 个字节的魔数
        out.writeBytes(new byte[]{1, 2, 3, 4});
        // 2. 1 个字节的版本
        out.writeByte(1);
        // 3. 1 个字节的序列化方式(jdk 为 0, json 为 1)
        out.writeByte(0);
        // 4. 1 个字节的消息类型
        out.writeByte(msg.getMessageType());
        // 5. 4 个字节的请求序列号
        out.writeInt(msg.getSequenceId());
        // 6. 无意义, 对其填充(共 16 个字节)
        out.writeByte(0xff);
        // 获取内容长度
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(msg);
        byte[] content = bos.toByteArray();
        // 7. 4 个字节长度
        out.writeInt(content.length);
        // 8. 消息正文
        out.writeBytes(content);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 1. 获取魔数
        int magicNum = in.readInt();
        // 2. 获取版本
        byte version = in.readByte();
        // 3. 获取序列化类型
        byte serializerType = in.readByte();
        // 4. 获取消息类型
        byte messageType = in.readByte();
        // 5. 获取请求序列号
        int sequenceId = in.readInt();
        // 6. 无意义字节
        in.readByte();
        // 7. 获取内容长度
        int length = in.readInt();
        // 8. 解析消息正文
        byte[] bytes = new byte[length];
        in.readBytes(bytes, 0, length);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Message message = (Message) ois.readObject();
        log.info("magicNum:{}, version:{}, serializerType:{}, messageType:{}, sequenceId:{}, length:{}",
                magicNum, version, serializerType, messageType, sequenceId, length);
        log.info("{}", message);
        out.add(message);
    }

}
