package com.elgin.netty.bio;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * bio服务器
 * @author 辰峰
 * @create 2020-05-25 22:39
 **/
public class BIOServer {

    public static void main(String[] args) throws IOException {

        LinkedBlockingQueue queue = new LinkedBlockingQueue();
        ExecutorService executorService = new ThreadPoolExecutor(5, 10, 1,
                TimeUnit.MILLISECONDS,queue);

        ServerSocket serverSocket = ServerSocketFactory.getDefault().createServerSocket(6666);
        System.out.println("server is running.....");
        while (true) {
           Socket socket = serverSocket.accept();
           System.out.println("client connect success...");
           executorService.submit(() -> {
               try {
//                   InputStream inputStream = socket.getInputStream();
//                   byte[] bytes = new byte[1024];
//                   while (inputStream.read(bytes) != -1){
//                       System.out.println(new String(bytes));
//                   }

                   String content = "hello ,i am thread :" + Thread.currentThread().getName();
                   OutputStream out = socket.getOutputStream();
                   out.write(content.getBytes());
               } catch (IOException e) {
                   e.printStackTrace();
               } finally {
                   try {
                       socket.close();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           });
        }

    }
}
