package com.beyond.thrift;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket socket = new ServerSocket(7912);
        TServerSocket serverTransport = new TServerSocket(socket);
        MyService.Processor<MyService.Iface> processor = new MyService.Processor<MyService.Iface>(new HelloMyService());
        TServer.AbstractServerArgs serverArgs = new TServer.Args(serverTransport).processor(processor);
        TServer server = new TSimpleServer(serverArgs);
        System.out.println("Running server...");
        server.serve();
    }
}
