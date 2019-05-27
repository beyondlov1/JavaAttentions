package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author beyondlov1
 * @date 2019/05/21
 */
public class ClientTest {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost",7788));
        OutputStream outputStream = socket.getOutputStream();
        byte[] bytes = "hello".getBytes();
        outputStream.write(bytes);
        outputStream.flush();

        InputStream inputStream = socket.getInputStream();
        int len;
        byte[] bytes1 = new byte[1024];
        while ((len = inputStream.read(bytes1))!=-1){
            System.out.println(new String(bytes1,0,len));
        }
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
