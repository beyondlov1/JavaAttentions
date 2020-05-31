package com.beyond.sync;


import java.util.Map;

public interface MultiDataSource<T>  extends DataSource<T>{
    String setChosenKey(String key);
    String getChosenKey();
    Map<String,SyncStamp> getSyncStampsCache();
    void setSyncStampsCache(Map<String,SyncStamp> syncStamps);
    void initLastSyncStamps();
}
