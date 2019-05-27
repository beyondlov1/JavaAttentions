package client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author beyondlov1
 * @date 2019/01/24
 */
public class ClientNio {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            Selector selector = Selector.open();
            //这里是有顺序的。要现配置，后connect
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost", 7788));
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

//            while (true) {
                selector.select();
                Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
                while (ite.hasNext()) {
                    SelectionKey key = ite.next();
                    ite.remove();
                    if (key.isConnectable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        //如果正在连接，则完成连接
                        if (channel.isConnectionPending()) {
                            channel.finishConnect();
                        }
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        Charset charset = Charset.defaultCharset();
                        while(channel.read(byteBuffer)!=-1){
                            byteBuffer.flip();
                            CharBuffer decode = charset.decode(byteBuffer);
                            System.out.println(decode);
                            byteBuffer.clear();
                        }
                    }

//                    else if (key.isReadable()) { //有可读数据事件。
//                        SocketChannel channel = (SocketChannel) key.channel();
//                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//                        Charset charset = Charset.defaultCharset();
//                        while(channel.read(byteBuffer)!=-1){
//                            byteBuffer.flip();
//                            CharBuffer decode = charset.decode(byteBuffer);
//                            System.out.println(decode);
//                            byteBuffer.clear();
//                        }
//                    }
                }
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
