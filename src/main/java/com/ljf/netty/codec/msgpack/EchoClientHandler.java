package com.ljf.netty.codec.msgpack;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;

/**
 * @author ray.zhao
 */
public class EchoClientHandler extends ChannelHandlerAdapter {

    /**
     * Creates a client-side handler.
     */
    public EchoClientHandler() {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        for (int i = 0; i < 1; i++) {
            ctx.write(UserInfo(i));
        }
        ctx.flush();
    }

    private ArrayList<String> UserInfo(int i) {
        ArrayList<String> src = new ArrayList<>();
        src.add("a" + i);
        src.add("b" + i);
        src.add("c" + i);
        return src;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("Receive server response : [" + msg + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
