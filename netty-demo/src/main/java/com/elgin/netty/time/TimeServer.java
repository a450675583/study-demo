package com.elgin.netty.time;

import com.elgin.netty.echo.EchoServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author zxs
 * 2020/10/27
 */
public class TimeServer {

    private int port;

    private TimeServer(int port){
        this.port = port;
    }

    public void  run() throws InterruptedException {

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss,worker).channel(NioServerSocketChannel.class)
                    .childHandler(new TimeServerHandler())
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);

            ChannelFuture channelFuture = bootstrap.bind(port).sync();

            channelFuture.channel().closeFuture().sync();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();

        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new TimeServer(port).run();
    }
}
