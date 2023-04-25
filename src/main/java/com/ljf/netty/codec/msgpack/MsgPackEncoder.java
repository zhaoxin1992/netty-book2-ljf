package com.ljf.netty.codec.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * @author ray.zhao
 */
public class MsgPackEncoder extends MessageToByteEncoder<Object> {


    @Override
    protected void encode(ChannelHandlerContext arg0, Object arg1, ByteBuf arg2) throws Exception {
        MessagePack messagePack = new MessagePack();
        byte[] bytes = messagePack.write(arg1);
        arg2.writeBytes(bytes);
    }
}
