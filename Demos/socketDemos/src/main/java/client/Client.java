package client;

import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author beyondlov1
 * @date 2019/01/23
 */
public class Client {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new MyTask(),0,1000);
    }
}

class MyTask extends TimerTask{

    @Override
    public void run() {
        Socket socket = null;
        InputStream is = null;
        BufferedReader br = null;
        RandomAccessFile raf = null;

        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            raf =  new RandomAccessFile(URLDecoder.decode(Client.class.getResource("").getPath(), "UTF-8")+"/test.txt","rw");
            socket = new Socket();
            socket.setKeepAlive(true);
            SocketAddress socketAddress = new InetSocketAddress("localhost",7878);
            socket.connect(socketAddress,1000); // 连接超时
            socket.setSoTimeout(1000); // SocketOption timeout 读超时
            is = socket.getInputStream();
            br =  new BufferedReader(new InputStreamReader(is));
            String info = null;
            raf.seek(raf.length());
            raf.write("\n".getBytes());
            while((info=br.readLine())!=null){
                System.out.println("我是客户端，服务器说："+info);
                raf.write("hello".getBytes());
            }
        } catch (IOException e) {
            sw = new StringWriter();
            pw =  new PrintWriter(sw, true);
            e.printStackTrace(pw);
            try {
                if (raf != null) {
                    raf.seek(raf.length());
                    raf.write("\n".getBytes());
                    raf.write(sw.toString().getBytes());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (raf != null) {
                    raf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (pw != null) {
                pw.close();
            }
            try {
                if (sw != null) {
                    sw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
