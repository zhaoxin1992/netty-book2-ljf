package com.ljf.netty.codec.msgpack;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;

import java.util.ArrayList;

/**
 * @author ray.zhao
 */
@Sharable
public class EchoServerHandler extends ChannelHandlerAdapter {

    int counter = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        ArrayList<String> userInfo = (ArrayList<String>) msg;
        System.out.println("This is " + ++counter + " times receive client : ["
                + userInfo + "]");
        ctx.writeAndFlush(userInfo);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();// 发生异常，关闭链路
    }

}
