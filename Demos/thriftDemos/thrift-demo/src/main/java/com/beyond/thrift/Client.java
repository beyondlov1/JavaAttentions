package com.beyond.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class Client {
    public static void main(String[] args) throws Exception {
        TTransport transport = new TSocket("localhost", 7912);
        TProtocol protocol = new TBinaryProtocol(transport);

        // 创建client
        MyService.Client client = new MyService.Client(protocol);

        transport.open();  // 建立连接

        User user = client.getUser();
        System.out.println(user);

        transport.close();  // 请求结束，断开连接
    }
}
