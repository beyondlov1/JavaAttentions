package com.beyond.sync;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class MockDataSource<T> implements DataSource<T> {

    static Random random = new Random();

    private String key = random.nextInt()+"号";

    List<T> list = new ArrayList<T>();

    public String getKey() {
        return key;
    }

    public void saveAll(List<T> ts) throws IOException {
        System.out.println(getKey()+":"+ts);
    }

    public void saveAll(List<T> ts, String source) throws IOException {

    }

    public List<T> selectAll() throws IOException, ExecutionException, InterruptedException {
        return null;
    }

    public boolean isChanged(DataSource<T> targetDataSource) throws IOException {
        return false;
    }

    public List<T> getChangedData(SyncStamp syncStamp) throws IOException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getKey()+" changed collected "+list);
        return list;
    }

    public SyncStamp getLastSyncStamp(DataSource<T> targetDataSource) throws IOException {
        return null;
    }

    public void updateLastSyncStamp(SyncStamp syncStamp, DataSource<T> targetDataSource) throws IOException {

    }

    public SyncStamp getLatestSyncStamp() throws IOException {
        return null;
    }

    public void updateLatestSyncStamp(SyncStamp syncStamp) throws IOException {

    }

    public Class<T> clazz() {
        return null;
    }

}
