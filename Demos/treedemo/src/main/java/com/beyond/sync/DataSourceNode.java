package com.beyond.sync;

import java.io.IOException;
import java.util.*;

public class DataSourceNode<T> {

    private DataSourceNode<T> parent;
    private List<DataSourceNode<T>> children = new ArrayList<DataSourceNode<T>>();
    private MultiDataSource<T> dataSource;
    private List<T> changedData = new ArrayList<T>();
    private Set<T> notMineData = new HashSet<T>();


    public static <T> DataSourceNode<T> of(MultiDataSource<T> dataSource) {
        DataSourceNode<T> node = new DataSourceNode<T>();
        node.setDataSource(dataSource);
        return node;
    }

    public List<T> getChildrenModifiedData() {
        List<T> childrenModifiedData = new ArrayList<T>(changedData);

        Iterator<DataSourceNode<T>> iterator = children.iterator();
        while (iterator.hasNext()) {
            DataSourceNode<T> child = iterator.next();
            try {
                childrenModifiedData.addAll(child.getModifiedData());
            } catch (Exception e) {
                // 把子节点从树中移除
                child.setParent(null);
                iterator.remove();
                continue;
            }
            childrenModifiedData.addAll(child.getChildrenModifiedData());
        }

        return childrenModifiedData;
    }

    private List<T> getModifiedData() throws IOException {
        MultiDataSource<T> parentDataSource = parent.getDataSource();
        SyncStamp lastSyncStamp = this.dataSource.getLastSyncStamp(parentDataSource);
        changedData = this.dataSource.getChangedData(lastSyncStamp);
        return changedData;
    }


    public void saveChildrenData(List<T> data) throws IOException {
        for (DataSourceNode<T> child : children) {
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

    public List<DataSourceNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<DataSourceNode<T>> children) {
        this.children = children;
        for (DataSourceNode<T> child : children) {
            child.setParent(this);
        }
    }

    public MultiDataSource<T> getDataSource() {
        return dataSource;
    }

    public void setDataSource(MultiDataSource<T> dataSource) {
        this.dataSource = dataSource;
    }

    public DataSourceNode getParent() {
        return parent;
    }

    public void setParent(DataSourceNode<T> parent) {
        this.parent = parent;
    }

    public DataSourceNode<T> addChild(DataSourceNode<T> node) {
        children.add(node);
        node.setParent(this);
        return this;
    }

}
