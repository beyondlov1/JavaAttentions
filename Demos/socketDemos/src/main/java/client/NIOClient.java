/*
 * @(#)NIOClient.java 2019-1-24上午11:02:05 Connection Copyright 2019 Thuisoft, Inc. All rights reserved. THUNISOFT PROPRIETARY/CONFIDENTIAL.
 * Use is subject to license terms.
 */
package client;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * NIOClient
 * 客户端
 * @author zhaozhe
 * @version 1.0
 *
 */
public class NIOClient {
    private static final Logger logger = LoggerFactory.getLogger(NIOClient.class);

    private static final long initTime = System.currentTimeMillis();

    public static void main(String[] args) {

        //定义处理编码和解码的字符集
        Charset charset = Charset.forName("UTF-8");
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.socket().setSoTimeout(3000);
            socketChannel.socket().connect(new InetSocketAddress("172.16.197.50", 30000), 3000);

            ByteBuffer writeBuffer = ByteBuffer.allocate(32);
            ByteBuffer readBuffer = ByteBuffer.allocate(32);

            writeBuffer.put(("hello" + initTime).getBytes());
            writeBuffer.flip();

            while (true) {
                writeBuffer.rewind();
                socketChannel.write(writeBuffer);
                readBuffer.clear();
                String content = "";
                while (socketChannel.read(readBuffer) > 0) {
                    readBuffer.flip();
                    content += charset.decode(readBuffer);
                    logger.info("客户端" + content);
                }
                Thread.sleep(3000);
            }
        } catch (Exception e) {
            logger.error("连接错误", e);
        }
    }
}
