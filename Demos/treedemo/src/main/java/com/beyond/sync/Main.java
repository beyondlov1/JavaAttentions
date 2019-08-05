package com.beyond.sync;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();
        AsyncDataSourceNode<Integer> root = constructNode();
        root.setExecutorService(getExecutorService());
        List<Integer> childrenModifiedData = root.getChildrenModifiedData();
        System.out.println(childrenModifiedData);
        long end = System.currentTimeMillis();

        System.out.println((end-start)/1000);

//        root.saveChildrenData(childrenModifiedData);
    }

    private static ExecutorService getExecutorService() {
        return new ThreadPoolExecutor(
                10, 20,
                10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(80));
    }

    private static AsyncDataSourceNode<Integer> constructNode() {
        return AsyncDataSourceNode.of(new IntegerMockDataSource())
                .addChild(
                        AsyncDataSourceNode.of(new IntegerMockDataSource())
                                .addChild(
                                        AsyncDataSourceNode.of(new ExceptionIntegerMockDataSource())
                                                .addChild(AsyncDataSourceNode.of(new IntegerMockDataSource())))
                                .addChild(
                                        AsyncDataSourceNode.of(new IntegerMockDataSource())
                                                .addChild(AsyncDataSourceNode.of(new IntegerMockDataSource()))
                                ))
                .addChild(
                        AsyncDataSourceNode.of(new IntegerMockDataSource())
                                .addChild(
                                        AsyncDataSourceNode.of(new IntegerMockDataSource())
                                                .addChild(AsyncDataSourceNode.of(new IntegerMockDataSource()))
                                ));

    }
}
