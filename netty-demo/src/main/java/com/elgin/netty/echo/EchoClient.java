package com.elgin.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class EchoClient {

    private String address;
    private int port;

    public EchoClient(String address,int port){
        this.address = address;
        this.port = port;
    }

    public void start() throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap;

        try {
            bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(address,port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new SimpleChannelInboundHandler<ByteBuf>() {

                                //建立连接后该 channelActive() 方法被调用一次
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    super.channelActive(ctx);
                                    ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
                                }

                                @Override
                                protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf o) throws Exception {
                                    log.info("client received msg: {}",o.toString(CharsetUtil.UTF_8));
                                }

                                //发生异常时调用
                                @Override
                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                    super.exceptionCaught(ctx, cause);
                                    log.error("",cause);
                                }
                            });
                        }
                    });
            //连接服务端，知道完成
            ChannelFuture future = bootstrap.connect().sync();
            //阻塞直到channel关闭
            future.channel().closeFuture().sync();
        }finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        new EchoClient("localhost",8080).start();;
    }
}
