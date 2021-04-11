package com.elgin.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * nio client
 * @author 辰峰
 * @create 2020-07-28 23:30
 **/
public class NIOClient {

    public static void main(String[] args) throws IOException {

        //创建一个客户端socketChannel
        SocketChannel socketChannel = SocketChannel.open();

        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8282);

        //设置为非阻塞模式
        socketChannel.configureBlocking(false);

        if(!socketChannel.connect(address)){

            while (!socketChannel.finishConnect()){
                //连接未就绪，可以做其他事情
                System.out.println("连接未就绪。。。。。");
            }

        }

        //连接成功，发送数据
        String requestData = "I am nio client;";
        ByteBuffer buffer = ByteBuffer.wrap(requestData.getBytes());
        socketChannel.write(buffer);

        System.in.read();
    }
}
