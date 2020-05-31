package com.beyond.sync;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface DataSource<T> {

    String getKey();

    void saveAll(List<T> tList) throws IOException;

    void saveAll(List<T> tList, String source) throws IOException;

    List<T> selectAll() throws IOException, ExecutionException, InterruptedException;

    boolean isChanged(DataSource<T> targetDataSource) throws IOException;

    List<T> getChangedData(SyncStamp syncStamp) throws IOException;

    SyncStamp getLastSyncStamp(DataSource<T> targetDataSource) throws IOException;

    void updateLastSyncStamp(SyncStamp syncStamp, DataSource<T> targetDataSource) throws IOException;

    SyncStamp getLatestSyncStamp() throws IOException;

    void updateLatestSyncStamp(SyncStamp syncStamp) throws IOException;

    Class<T> clazz();

}
