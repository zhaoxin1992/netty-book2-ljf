package com.ljf.netty.protocol.http.xml.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import com.ljf.netty.protocol.http.xml.codec.HttpXmlRequest;
import com.ljf.netty.protocol.http.xml.codec.HttpXmlResponse;
import com.ljf.netty.protocol.http.xml.pojo.OrderFactory;

/**
 * @author Administrator
 * @date 2014年2月16日
 * @version 1.0
 */
public class HttpXmlClientHandle extends
	SimpleChannelInboundHandler<HttpXmlResponse> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
	HttpXmlRequest request = new HttpXmlRequest(null,
		OrderFactory.create(123));
	ctx.writeAndFlush(request);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	cause.printStackTrace();
	ctx.close();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx,
	    HttpXmlResponse msg) throws Exception {
	System.out.println("The client receive response of http header is : "
		+ msg.getHttpResponse().headers().names());
	System.out.println("The client receive response of http body is : "
		+ msg.getResult());
    }
}
