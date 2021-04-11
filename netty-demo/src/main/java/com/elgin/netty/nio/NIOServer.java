package com.elgin.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * nio server
 * @author 辰峰
 * @create 2020-07-28 22:56
 **/
public class NIOServer {

    public static void main(String[] args) throws IOException {

        //创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定端口
        serverSocketChannel.bind(new InetSocketAddress(8282));
        //设置serverSocketChannel为非阻塞模式
        serverSocketChannel.configureBlocking(false);

        //创建选择器
        Selector selector = Selector.open();

        //把serverSocketChannel绑定到selector，并只关注连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //等待客户端连接
        while (true){

            //检测是否有连接请求
            if (selector.select(2000) == 0){
                System.out.println("等待2秒没有连接请求。。。继续监听");
                continue;
            }

            //如果selector.select(2000)大于0 表示已经获取到对应的关注事件
            //通过selector.selectedKeys()返回关注事件的集合
            //通过SelectionKey反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();

                //如果是OP_ACCEPT事件，表示有新客户端连接
                if(key.isAcceptable()){
                    //为客户端生成一个socketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功，生成一个socketChannel：" + socketChannel);

                    //将socketChannel配置为非阻塞
                    socketChannel.configureBlocking(false);

                    //再将socketChannel注册到选择器, 关注读事件，并为channel关联一个 bytebuffer
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                }

                //如果是OP_READ ,表示发生可读事件
                if(key.isReadable()){
                    //通过key反向获取到channel
                    SocketChannel socketChannel = (SocketChannel) key.channel();

                    //获取到channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();

                    //从channel读取数据到buffer
                    socketChannel.read(buffer);

                    System.out.println("客户端请求数据为：" + new String(buffer.array()));
                }

                //手动清除selectionKey 防止重复操作
                keyIterator.remove();
            }

        }


    }
}
