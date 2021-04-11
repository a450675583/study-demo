package com.elgin.netty.nio;

import java.nio.IntBuffer;

/**
 *
 * @author 辰峰
 * @create 2020-06-15 23:38
 **/
public class IntBufferTest {

    public static void main(String[] args) {

        IntBuffer intBuffer = IntBuffer.allocate(5);
        System.out.printf(intBuffer.toString());
    }
}
