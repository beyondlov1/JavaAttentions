package com.beyond.sync;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main2 {
    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();

        // init ModifiedData
        AsyncDataSourceNode2<Integer> root = constructNode();
        ArrayList<AsyncDataSourceNode2<Integer>> allChildren = new ArrayList<AsyncDataSourceNode2<Integer>>();
        root.getAllChildren(allChildren);
        List<Future<List<Integer>>> futures = new ArrayList<Future<List<Integer>>>();
        for (final AsyncDataSourceNode2<Integer> child : allChildren) {
            Future<List<Integer>> future = getExecutorService().submit(new Callable<List<Integer>>() {
                public List<Integer> call() throws Exception {
                    child.initModifiedData();
                    return null;
                }
            });
            futures.add(future);
        }
        int index = 0 ;
        for (Future<List<Integer>> future : futures) {
            try {
                future.get();
            }catch (Exception e) {
                e.printStackTrace();
                // 把子节点从树中移除
                AsyncDataSourceNode2<Integer> child = allChildren.get(index);
                child.getParent().getChildren().remove(child);
                child.setParent(null);
            }
            index++;
        }

        List<Integer> childrenModifiedData = root.getValidChildrenModifiedData();
        System.out.println(childrenModifiedData);

        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000);

        root.saveChildrenData(childrenModifiedData);
    }

    private static ExecutorService getExecutorService() {
        return new ThreadPoolExecutor(
                10, 20,
                10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(80));
    }

    private static AsyncDataSourceNode2<Integer> constructNode() {
        return AsyncDataSourceNode2.of(new IntegerMockDataSource("root",""))
                .addChild(
                        AsyncDataSourceNode2.of(new IntegerMockDataSource("1.1",""))
                                .addChild(
                                        AsyncDataSourceNode2.of(new ExceptionIntegerMockDataSource("2.1",""))
                                                .addChild(AsyncDataSourceNode2.of(new IntegerMockDataSource("3.1",""))))
                                .addChild(
                                        AsyncDataSourceNode2.of(new IntegerMockDataSource("2.2",""))
                                                .addChild(AsyncDataSourceNode2.of(new IntegerMockDataSource("3.2","")))
                                ))
                .addChild(
                        AsyncDataSourceNode2.of(new IntegerMockDataSource("1.2",""))
                                .addChild(
                                        AsyncDataSourceNode2.of(new IntegerMockDataSource("2.3",""))
                                                .addChild(AsyncDataSourceNode2.of(new IntegerMockDataSource("3.3","")))
                                ));

    }
}
