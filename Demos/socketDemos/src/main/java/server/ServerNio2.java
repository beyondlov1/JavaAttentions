package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * @author beyondlov1
 * @date 2019/05/21
 */
public class ServerNio2 {
    public static void main(final String[] args) throws IOException {
        final CountDownLatch latch = new CountDownLatch(1);
        final AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(7788));
        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(final AsynchronousSocketChannel channel, Object attachment) {
                // 重新监听连接
                serverSocketChannel.accept(null, this);

                // 读取数据
                final ByteBuffer byteBuffer = ByteBuffer.allocate(256);
                channel.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer buffer) {
                        final CompletionHandler<Integer, ByteBuffer> that = this;
                        buffer.flip();
                        while (buffer.hasRemaining()) {
                            System.out.println((char) buffer.get());
                        }
                        buffer.flip();
                        channel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                            @Override
                            public void completed(Integer result, ByteBuffer attachment) {
                                if (attachment.hasRemaining()) {
                                    channel.write(attachment, attachment, this);
                                } else {
                                    attachment.compact();
                                    channel.read(attachment, attachment, that);
                                }
                            }

                            @Override
                            public void failed(Throwable exc, ByteBuffer attachment) {

                            }
                        });

                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {

                    }
                });
            }

            @Override
            public void failed(Throwable exc, Object attachment) {

            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
