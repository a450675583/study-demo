package com.elgin.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.Instant;

/**
 * @author zxs
 * 2020/10/27
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 因为我们将会忽略任何接收到的数据，连接被创建时发送一个消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf time = ctx.alloc().buffer(4);
        time.writeLong(Instant.now().getEpochSecond());

        ChannelFuture future = ctx.writeAndFlush(time);

        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                ctx.close();
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
        cause.printStackTrace();
    }
}
