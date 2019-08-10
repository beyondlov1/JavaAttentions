package com.beyond.sync;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static com.beyond.sync.MultiExecuteUtils.blockExecute;

public class Main3 {
    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();

        AsyncDataSourceNode2<Integer> root = constructNode();

        initModifiedData(root);

        List<Integer> childrenModifiedData = root.getValidChildrenModifiedData();

        System.out.println(childrenModifiedData);

        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000);

        root.saveChildrenData(childrenModifiedData);
    }

    private static void initModifiedData(AsyncDataSourceNode2<Integer> root) throws IOException {
        ArrayList<AsyncDataSourceNode2<Integer>> allChildren = new ArrayList<AsyncDataSourceNode2<Integer>>();
        root.getAllChildren(allChildren);

        blockExecute(getExecutorService(),
                new MultiExecuteUtils.ParamCallable<AsyncDataSourceNode2<Integer>>() {
                    public void call(AsyncDataSourceNode2<Integer> singleExecutor) throws Exception {
                        singleExecutor.initModifiedData();
                    }
                }, new MultiExecuteUtils.ParamCallable<AsyncDataSourceNode2<Integer>>() {
                    public void call(AsyncDataSourceNode2<Integer> singleExecutor) throws Exception {
                        // 把子节点从树中移除
                        singleExecutor.getParent().getChildren().remove(singleExecutor);
                        singleExecutor.setParent(null);
                    }
                }, allChildren);

    }

    private static ExecutorService getExecutorService() {
        return new ThreadPoolExecutor(
                10, 20,
                10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(80));
    }

    private static AsyncDataSourceNode2<Integer> constructNode() {
        List<MultiDataSource<Integer>> list = new ArrayList<MultiDataSource<Integer>>();
        list.add(new IntegerMockDataSource("1.1", "root"));
        list.add(new IntegerMockDataSource("1.2", "root"));
        list.add(new IntegerMockDataSource("1.3", "root"));
        list.add(new IntegerMockDataSource("1.1.1", "1.1"));
        list.add(new IntegerMockDataSource("1.1.2", "1.1"));
        list.add(new ExceptionIntegerMockDataSource("1.1.3", "1.1"));
        list.add(new IntegerMockDataSource("1.2.1", "1.2"));
        list.add(new IntegerMockDataSource("1.2.2", "1.2"));
        list.add(new IntegerMockDataSource("1.2.3", "1.2"));
        list.add(new IntegerMockDataSource("1.3.1", "1.3"));
        list.add(new IntegerMockDataSource("1.3.2", "1.3"));
        list.add(new IntegerMockDataSource("1.3.3", "1.3"));
        list.add(new IntegerMockDataSource("1.3.1.1", "1.3.1"));
        list.add(new IntegerMockDataSource("1.1.2.1", "1.1.2"));
        list.add(new IntegerMockDataSource("1.3.3.1", "1.3.3"));
        list.add(new IntegerMockDataSource("1.3.3.9", "1.3.6"));

        AsyncDataSourceNode2<Integer> root = chooseRoot(list);

        constructTree(list, root);
        return root;
    }


    private static void constructTree(List<MultiDataSource<Integer>> list, AsyncDataSourceNode2<Integer> rootNode) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<MultiDataSource<Integer>> iterator = list.iterator();
        while (iterator.hasNext()) {
            MultiDataSource<Integer> next = iterator.next();
            if (rootNode.getDataSource().getKey().equals(next.getChosenKey())) {
                rootNode.addChild(AsyncDataSourceNode2.of(next));
                iterator.remove();
            }
        }
        for (AsyncDataSourceNode2<Integer> child : rootNode.getChildren()) {
            constructTree(list, child);
        }
    }


    private static AsyncDataSourceNode2<Integer> chooseRoot(List<MultiDataSource<Integer>> list) {
        initSyncStamps(list);
        initSyncKeyForSync(list);
        return AsyncDataSourceNode2.of(getMaxConnectedDataSource(list));
    }

    private static MultiDataSource<Integer> getMaxConnectedDataSource(List<MultiDataSource<Integer>> list) {
        MultiDataSource<Integer> result = list.get(0);
        int resultCount = getConnectedDataSourceCount(result,list);
        for (MultiDataSource<Integer> dataSource : list) {
            int count = getConnectedDataSourceCount(dataSource,list);
            if (count>resultCount){
                result = dataSource;
                resultCount = count;
            }
        }
        return result;
    }

    private static int getConnectedDataSourceCount(MultiDataSource<Integer> dataSource, List<MultiDataSource<Integer>> list) {
        int count = 0;
        for (MultiDataSource<Integer> ds : list) {
            if (dataSource.getKey().equals(ds.getChosenKey())){
                count++;
            }
        }
        return count;
    }

    private static void initSyncKeyForSync(List<MultiDataSource<Integer>> list) {
        for (MultiDataSource<Integer> dataSource : list) {
            chooseInternal(list, dataSource);
        }
    }

    private static void chooseInternal(List<MultiDataSource<Integer>> list, MultiDataSource<Integer> dataSource) {
        Map<String, SyncStamp> syncStamps = dataSource.getSyncStampsCache();
        for (String key : syncStamps.keySet()) {
            boolean found = false;
            for (MultiDataSource<Integer> ds : list) {
                if (ds.getKey().equals(key)){
                    found = true;
                    break;
                }
            }
            if (found){
                dataSource.setChosenKey(key);
                break;
            }
        }
    }

    private static void initSyncStamps(List<MultiDataSource<Integer>> list) {
        blockExecute(getExecutorService(),
                new MultiExecuteUtils.ParamCallable<MultiDataSource<Integer>>() {
                    public void call(MultiDataSource<Integer> singleExecutor) throws Exception {
                        singleExecutor.initLastSyncStamps();
                    }
                }, new MultiExecuteUtils.ParamCallable<MultiDataSource<Integer>>() {
                    public void call(MultiDataSource<Integer> singleExecutor) throws Exception {
                        System.out.println(singleExecutor);
                    }
                }, list);
    }


}
