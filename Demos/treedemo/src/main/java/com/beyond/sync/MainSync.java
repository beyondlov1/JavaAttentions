package com.beyond.sync;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainSync {
    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();
        DataSourceNode<Integer> root = constructNode();
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

    private static DataSourceNode<Integer> constructNode() {
        return DataSourceNode.of(new IntegerMockDataSource())
                .addChild(
                        DataSourceNode.of(new IntegerMockDataSource())
                                .addChild(
                                        DataSourceNode.of(new ExceptionIntegerMockDataSource())
                                                .addChild(DataSourceNode.of(new IntegerMockDataSource())))
                                .addChild(
                                        DataSourceNode.of(new IntegerMockDataSource())
                                                .addChild(DataSourceNode.of(new IntegerMockDataSource()))
                                ))
                .addChild(
                        DataSourceNode.of(new IntegerMockDataSource())
                                .addChild(
                                        DataSourceNode.of(new IntegerMockDataSource())
                                                .addChild(DataSourceNode.of(new IntegerMockDataSource()))
                                ));

    }
}
