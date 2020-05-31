package com.beyond.sync;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;

public class AsyncDataSourceNode2<T> {

    private AsyncDataSourceNode2<T> parent;
    private List<AsyncDataSourceNode2<T>> children = new ArrayList<AsyncDataSourceNode2<T>>();
    private MultiDataSource<T> dataSource;
    private List<T> modifiedData = new ArrayList<T>();
    private Set<T> notMineData = new HashSet<T>();
    private ExecutorService executorService;


    public static <T> AsyncDataSourceNode2<T> of(MultiDataSource<T> dataSource) {
        AsyncDataSourceNode2<T> node = new AsyncDataSourceNode2<T>();
        node.setDataSource(dataSource);
        return node;
    }

    public void getAllChildren(List<AsyncDataSourceNode2<T>> result) {
        for (AsyncDataSourceNode2<T> child : children) {
            result.add(child);
            child.getAllChildren(result);
        }
    }

    public List<T> getValidChildrenModifiedData() {
        List<T> childrenModifiedData = new ArrayList<T>();
        for (AsyncDataSourceNode2<T> child : children) {
            childrenModifiedData.addAll(child.modifiedData);
            childrenModifiedData.addAll(child.getValidChildrenModifiedData());
        }
        return childrenModifiedData;
    }

    private ExecutorService getExecutorService() {
        return executorService;
    }

    public AsyncDataSourceNode2<T> setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
        for (AsyncDataSourceNode2<T> child : children) {
            child.setExecutorService(executorService);
        }
        return this;
    }

    public List<T> initModifiedData() throws IOException {
        MultiDataSource<T> parentDataSource = parent.getDataSource();
        SyncStamp lastSyncStamp = this.dataSource.getLastSyncStamp(parentDataSource);
        modifiedData = this.dataSource.getChangedData(lastSyncStamp);
        return modifiedData;
    }


    public void saveChildrenData(List<T> data) throws IOException {
        for (AsyncDataSourceNode2<T> child : children) {
            child.saveData(data);
            child.saveChildrenData(data);
        }
    }

    private void saveData(List<T> data) throws IOException {
        notMineData.addAll(data);
        notMineData.removeAll(modifiedData);
        dataSource.saveAll(new ArrayList<T>(notMineData));
    }


    public Set<T> getNotMineData() {
        return notMineData;
    }

    public void setNotMineData(Set<T> notMineData) {
        this.notMineData = notMineData;
    }

    public List<AsyncDataSourceNode2<T>> getChildren() {
        return children;
    }

    public void setChildren(List<AsyncDataSourceNode2<T>> children) {
        this.children = children;
        for (AsyncDataSourceNode2<T> child : children) {
            child.setParent(this);
        }
    }

    public MultiDataSource<T> getDataSource() {
        return dataSource;
    }

    public void setDataSource(MultiDataSource<T> dataSource) {
        this.dataSource = dataSource;
    }

    public AsyncDataSourceNode2 getParent() {
        return parent;
    }

    public void setParent(AsyncDataSourceNode2<T> parent) {
        this.parent = parent;
    }

    public AsyncDataSourceNode2<T> addChild(AsyncDataSourceNode2<T> node) {
        node.setParent(this);
        children.add(node);
        return this;
    }

}
