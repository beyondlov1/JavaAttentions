package com.beyond.sync;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public class AsyncDataSourceNode<T> {

    private AsyncDataSourceNode<T> parent;
    private List<AsyncDataSourceNode<T>> children = new ArrayList<AsyncDataSourceNode<T>>();
    private DataSource<T> dataSource;
    private List<T> changedData = new ArrayList<T>();
    private Set<T> notMineData = new HashSet<T>();
    private ExecutorService executorService;


    public static <T> AsyncDataSourceNode<T> of(DataSource<T> dataSource) {
        AsyncDataSourceNode<T> node = new AsyncDataSourceNode<T>();
        node.setDataSource(dataSource);
        return node;
    }

    public List<T> getChildrenModifiedData() {
        final List<T> childrenModifiedData = new ArrayList<T>(changedData);

        List<Future<List<T>>> futures = new ArrayList<Future<List<T>>>();
        for (final AsyncDataSourceNode<T> child : children) {
            Future<List<T>> future = executorService.submit(new Callable<List<T>>() {
                public List<T> call() throws Exception {
                    List<T> childrenModifiedData = new ArrayList<T>();
                    childrenModifiedData.addAll(child.getModifiedData());
                    childrenModifiedData.addAll(child.getChildrenModifiedData());
                    return childrenModifiedData;
                }
            });
            futures.add(future);
        }

        int index = 0 ;
        for (Future<List<T>> future : futures) {
            try {
                childrenModifiedData.addAll(future.get());
            }catch (Exception e) {
                // 把子节点从树中移除
                AsyncDataSourceNode<T> child = children.get(index);
                child.setParent(null);
                children.remove(child);
            }
            index++;
        }

        return childrenModifiedData;
    }

    private ExecutorService getExecutorService() {
        return executorService;
    }

    public AsyncDataSourceNode<T> setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
        for (AsyncDataSourceNode<T> child : children) {
            child.setExecutorService(executorService);
        }
        return this;
    }

    private List<T> getModifiedData() throws IOException {
        DataSource<T> parentDataSource = parent.getDataSource();
        SyncStamp lastSyncStamp = this.dataSource.getLastSyncStamp(parentDataSource);
        changedData = this.dataSource.getChangedData(lastSyncStamp);
        return changedData;
    }


    public void saveChildrenData(List<T> data) throws IOException {
        for (AsyncDataSourceNode<T> child : children) {
            child.saveData(data);
            child.saveChildrenData(data);
        }
    }

    private void saveData(List<T> data) throws IOException {
        notMineData.addAll(data);
        notMineData.removeAll(changedData);
        dataSource.saveAll(new ArrayList<T>(notMineData));
    }


    public Set<T> getNotMineData() {
        return notMineData;
    }

    public void setNotMineData(Set<T> notMineData) {
        this.notMineData = notMineData;
    }

    public List<AsyncDataSourceNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<AsyncDataSourceNode<T>> children) {
        this.children = children;
        for (AsyncDataSourceNode<T> child : children) {
            child.setParent(this);
        }
    }

    public DataSource<T> getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource<T> dataSource) {
        this.dataSource = dataSource;
    }

    public AsyncDataSourceNode getParent() {
        return parent;
    }

    public void setParent(AsyncDataSourceNode<T> parent) {
        this.parent = parent;
    }

    public AsyncDataSourceNode<T> addChild(AsyncDataSourceNode<T> node) {
        node.setParent(this);
        children.add(node);
        return this;
    }

}
