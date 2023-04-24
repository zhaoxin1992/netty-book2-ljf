package com.ljf.netty.ssl;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * Creates a newly configured {@link ChannelPipeline} for a new channel.
 */
public class SecureChatClientInitializer extends
        ChannelInitializer<SocketChannel> {

    private String tlsMode;

    public SecureChatClientInitializer(String tlsMode) {
        this.tlsMode = tlsMode;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // Add SSL handler first to encrypt and decrypt everything.
        // In this example, we use a bogus certificate in the server side
        // and accept any invalid certificates in the client side.
        // You will need something more complicated to identify both
        // and server in the real world.

        SSLEngine engine = null;
        if (SSLMODE.CA.toString().equals(tlsMode)) {
            engine = SecureChatSslContextFactory
                    .getClientContext(
                            tlsMode,
                            null,
                            System.getProperty("user.dir")
                                    + "/src/com/ljf/netty/ssl/conf/client/cChat.jks")
                    .createSSLEngine();
        } else if (SSLMODE.CSA.toString().equals(tlsMode)) {
            engine = SecureChatSslContextFactory
                    .getClientContext(
                            tlsMode,
                            System.getProperty("user.dir")
                                    + "/src/com/ljf/netty/ssl/conf/twoway/cChat.jks",
                            System.getProperty("user.dir")
                                    + "/src/com/ljf/netty/ssl/conf/twoway/cChat.jks")
                    .createSSLEngine();

            // engine = SecureChatSslContextFactory
            // .getClientContext(
            // tlsMode,
            // System.getProperty("user.dir")
            // + "/src/com/ljf/netty/ssl/conf/client/cChat.jks",
            // System.getProperty("user.dir")
            // + "/src/com/ljf/netty/ssl/conf/client/cChat.jks")
            // .createSSLEngine();

        } else {
            System.err.println("ERROR : " + tlsMode);
            System.exit(-1);
        }
        engine.setUseClientMode(true);
        pipeline.addLast("ssl", new SslHandler(engine));

        // On top of the SSL handler, add the text line codec.
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192,
                Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        // and then business logic.
        pipeline.addLast("handler", new SecureChatClientHandler());
    }
}
