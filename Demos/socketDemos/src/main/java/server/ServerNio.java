package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author beyondlov1
 * @date 2019/01/24
 */
public class ServerNio {

    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            ServerSocket socket = serverSocketChannel.socket();
            socket.bind(new InetSocketAddress("localhost", 7878));

            Selector selector = Selector.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


            while (true) {
                try {
                    int select = selector.select();
                    if (select == 0) {
                        continue;
                    }
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isAcceptable()) {
                            ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) key.channel();
                            SocketChannel socketChannel = serverSocketChannel1.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.write(ByteBuffer.wrap("hello1".getBytes("UTF-8")));
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            socketChannel.close();
                        }

//                    else if (key.isReadable()){
//                        SocketChannel channel = (SocketChannel)key.channel();
//                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//                        channel.read(byteBuffer);
//                        byteBuffer.flip();
//                        while (byteBuffer.hasRemaining()) {
//                            System.out.print((char) byteBuffer.get());
//                        }
//                        channel.configureBlocking(false);
//                        channel.register(selector,SelectionKey.OP_READ);
//                    }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
