package io.jacy.drafts.io.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import org.junit.Test;

import java.net.InetSocketAddress;

/**
 * @author Jacy
 */
public class TestNetty {
    @Test
    public void clientModel() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup(1);

        NioSocketChannel client = new NioSocketChannel();

        group.register(client);

        client.pipeline().addLast(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel socketChannel) {
                socketChannel.pipeline().addLast(new ReadHandler());
            }
        });


        ChannelFuture connectFuture = client.connect(new InetSocketAddress("10.200.24.74", 9090));
        connectFuture.sync();
    }
}

class ReadHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

//        CharSequence charSequence = byteBuf.readCharSequence(byteBuf.readableBytes(), CharsetUtil.UTF_8);
        CharSequence charSequence = byteBuf.getCharSequence(0, byteBuf.readableBytes(), CharsetUtil.UTF_8);
        System.out.println(charSequence);
        ctx.writeAndFlush(byteBuf);
    }
}