package org.masteryourself.tutorial.netty.pack.rpc.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.netty.pack.rpc.Config;
import org.masteryourself.tutorial.netty.pack.rpc.message.Message;

import java.util.List;

@Slf4j
@ChannelHandler.Sharable
public class MessageCodecSharable extends MessageToMessageCodec<ByteBuf, Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> outList) throws Exception {
        ByteBuf out = ctx.alloc().buffer();
        // 1. 4 个字节的魔数
        out.writeBytes(new byte[]{1, 2, 3, 4});
        // 2. 1 字节的版本,
        out.writeByte(1);
        // 3. 1 字节的序列化方式 jdk 0 , json 1
        out.writeByte(Config.getSerializerAlgorithm().ordinal());
        // 4. 1 字节的指令类型
        out.writeByte(msg.getMessageType());
        // 5. 4 个字节
        out.writeInt(msg.getSequenceId());
        // 无意义，对齐填充
        out.writeByte(0xff);
        // 6. 获取内容的字节数组
        byte[] bytes = Config.getSerializerAlgorithm().serialize(msg);
        // 7. 长度
        out.writeInt(bytes.length);
        // 8. 写入内容
        out.writeBytes(bytes);
        outList.add(out);
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
        SerializerAlgorithm algorithm = SerializerAlgorithm.getByType(serializerType);
        Class<? extends Message> messageClass = Message.getMessageClass(messageType);
        Message message = algorithm.deserialize(messageClass, bytes);
        out.add(message);
    }

}
