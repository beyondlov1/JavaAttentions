/*
 * @(#)NIOServer.java 2019-1-24上午11:01:54 Connection Copyright 2019 Thuisoft, Inc. All rights reserved. THUNISOFT PROPRIETARY/CONFIDENTIAL.
 * Use is subject to license terms.
 */
package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * NIOServer
 * 服务端
 * @author zhaozhe
 * @version 1.0
 *
 */
public class NIOServer {

    //定义实现编码、解码的字符集对象
    private Charset charset = Charset.forName("UTF-8");

    protected static final Logger logger = LoggerFactory.getLogger(NIOServer.class);

    public void init() throws IOException {
        //用于检测所有Channel状态的Selector
        Selector selector = Selector.open();
        //通过open方法来打开一个未绑定的ServerSocketChannel实例
        ServerSocketChannel server = ServerSocketChannel.open();
        InetSocketAddress isa = new InetSocketAddress("172.16.197.50", 30000);
        //将该ServerSocketChannel绑定到指定ip地址
        server.socket().bind(isa);
        //设置ServerSocket以非阻塞方式工作
        server.configureBlocking(false);
        //将server注册到指定Selector对象
        server.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            try {
                while (it.hasNext()) {
                    SelectionKey sk = it.next();

                    it.remove();
                    //如果sk对应的通信包含客户端的连接请求
                    if (sk.isAcceptable()) {
                        //调用accept方法接受连接，产生服务器端对应的SocketChannel
                        SocketChannel sc = server.accept();
                        //设置采用非阻塞模式
                        sc.configureBlocking(false);
                        sc.register(selector, SelectionKey.OP_READ);
                        //将sk对应的Channel设置成准备接受其他请求
                        sk.interestOps(SelectionKey.OP_ACCEPT);
                    }
                    //如果sk对应的通道有数据需要读取
                    if (sk.isReadable()) {
                        //获取该SelectionKey对应的Channel，该Channel中有可读的数据
                        SocketChannel sc = (SocketChannel) sk.channel();
                        //定义准备之星读取数据的ByteBuffer
                        ByteBuffer buff = ByteBuffer.allocate(1024);
                        String content = "";
                        //开始读取数据
                        try {
                            while (sc.read(buff) > 0) {
                                buff.flip();
                                content += charset.decode(buff);
                            }
                            logger.info("服务端" + sc.getLocalAddress() + "来自客户端:" + sc.getRemoteAddress() + "发来的信息:" + content);
                            if(content.length() >0){
                                sc.write(charset.encode(content));
                            }
                            //将sk对应的Channel设置成准备下一次读取
                            sk.interestOps(SelectionKey.OP_READ);
                            //如果捕捉到该sk对应的channel出现异常，即表明该channel对应的client出现了
                            //异常，所以从selector中取消sk的注册
                        } catch (IOException e) {
                            //从Selector中删除指定的SelectionKey
                            sk.cancel();
                            if (sk.channel() != null) {
                                sk.channel().close();
                            }
                            logger.error("请求服务端的客户端异常", e);
                        }

//                        if (content.length() > 0) {
//                            for (SelectionKey key : selector.keys()) {
//                                Channel targetChannel = key.channel();
//                                if (targetChannel instanceof SocketChannel) {
//                                    SocketChannel dest = (SocketChannel) targetChannel;
//                                    dest.write(charset.encode(content));
//                                }
//                            }
//                        }
                    }
                }
            } catch (Exception e) {
                it.remove();
                logger.error("服务端关闭异常", e);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new NIOServer().init();
    }

}
