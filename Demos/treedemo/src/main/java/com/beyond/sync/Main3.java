package com.beyond.sync;

import com.sun.corba.se.impl.presentation.rmi.ExceptionHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class Main3 {
    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();

        AsyncDataSourceNode2<Integer> root = constructNode();

        initModifiedData(root);

        List<Integer> childrenModifiedData = root.getValidChildrenModifiedData();

        System.out.println(childrenModifiedData);

        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000);

        root.saveChildrenData(childrenModifiedData);
    }

    private static void initModifiedData(AsyncDataSourceNode2<Integer> root) throws IOException {
        ArrayList<AsyncDataSourceNode2<Integer>> allChildren = new ArrayList<AsyncDataSourceNode2<Integer>>();
        root.getAllChildren(allChildren);

        blockExecute(new ParamCallable<AsyncDataSourceNode2<Integer>>() {
            public void call(AsyncDataSourceNode2<Integer> singleExecutor) throws Exception {
                singleExecutor.initModifiedData();
            }
        },new ParamCallable<AsyncDataSourceNode2<Integer>>() {
            public void call(AsyncDataSourceNode2<Integer> singleExecutor) throws Exception {
                // 把子节点从树中移除
                singleExecutor.getParent().getChildren().remove(singleExecutor);
                singleExecutor.setParent(null);
            }
        },allChildren);

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

        chooseRoot(list);

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
        initSyncStamps(list);
//        filterIsolated(list);
//        sortCandidate(list);
//        choose(list);
        return null;
    }

    private static void initSyncStamps(List<MultiDataSource<Integer>> list) {
        blockExecute(new ParamCallable<MultiDataSource<Integer>>() {
            public void call(MultiDataSource<Integer> singleExecutor) throws Exception {
                singleExecutor.initLastSyncStamps();
            }
        },new ParamCallable<MultiDataSource<Integer>>() {
            public void call(MultiDataSource<Integer> singleExecutor) throws Exception {
                System.out.println(singleExecutor);
            }
        },list);
    }

    @SuppressWarnings("unchecked")
    private static  <V> void blockExecute(final ParamCallable<V> callable,
                                          ParamCallable<V> exceptionHandler,
                                          List<V> targetList){
        List<Future<?>> futures = new ArrayList<Future<?>>();

        for (final V object : targetList) {
            Future<?> future = getExecutorService().submit(new Callable<Object>() {
                public Object call() throws Exception {
                    try {
                        callable.call(object);
                    }catch (Exception e){
                        BlockExecuteException exception = new BlockExecuteException();
                        exception.setObject(object);
                        exception.initCause(e);
                        throw exception;
                    }
                    return object;
                }
            });
            futures.add(future);
        }
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                if (exceptionHandler == null){
                    return;
                }
                if (e.getCause() instanceof BlockExecuteException){
                    try {
                        exceptionHandler.call((V) ((BlockExecuteException)(e.getCause())).getObject());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    private interface ParamCallable<V>{
        void call(V singleExecutor) throws Exception;
    }

    private static class BlockExecuteException extends Exception{

        private Object object;

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }
    }

}
