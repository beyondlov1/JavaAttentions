package com.beyond.sync;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class Main3 {
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
        List<MultiDataSource<Integer>> list = new ArrayList<MultiDataSource<Integer>>();
        list.add(new IntegerMockDataSource("1.1","root"));
        list.add(new IntegerMockDataSource("1.2","root"));
        list.add(new IntegerMockDataSource("1.3","root"));
        list.add(new IntegerMockDataSource("1.1.1","1.1"));
        list.add(new IntegerMockDataSource("1.1.2","1.1"));
        list.add(new ExceptionIntegerMockDataSource("1.1.3","1.1"));
        list.add(new IntegerMockDataSource("1.2.1","1.2"));
        list.add(new IntegerMockDataSource("1.2.2","1.2"));
        list.add(new IntegerMockDataSource("1.2.3","1.2"));
        list.add(new IntegerMockDataSource("1.3.1","1.3"));
        list.add(new IntegerMockDataSource("1.3.2","1.3"));
        list.add(new IntegerMockDataSource("1.3.3","1.3"));
        list.add(new IntegerMockDataSource("1.3.1.1","1.3.1"));
        list.add(new IntegerMockDataSource("1.1.2.1","1.1.2"));
        list.add(new IntegerMockDataSource("1.3.3.1","1.3.3"));
        list.add(new IntegerMockDataSource("1.3.3.9","1.3.6"));

        AsyncDataSourceNode2<Integer> root = AsyncDataSourceNode2.of(new IntegerMockDataSource("root", ""));
        constructTree(list,root);
        return root;
    }


    private static void constructTree(List<MultiDataSource<Integer>> list, AsyncDataSourceNode2<Integer> rootNode){
        if (list== null || list.isEmpty()){
            return;
        }
        Iterator<MultiDataSource<Integer>> iterator = list.iterator();
        while (iterator.hasNext()) {
            MultiDataSource<Integer> next = iterator.next();
            if (rootNode.getDataSource().getKey().equals(next.getChosenLastSyncKey())){
                rootNode.addChild(AsyncDataSourceNode2.of(next));
                iterator.remove();
            }
        }
        for (AsyncDataSourceNode2<Integer> child : rootNode.getChildren()) {
            constructTree(list,child);
        }
    }


    private static AsyncDataSourceNode2<Integer> chooseRoot(List<MultiDataSource<Integer>> list){
//        init(list);
//        filterInner(list);
//        sortInner(list);
//        choose(list);
        return null;
    }
}
