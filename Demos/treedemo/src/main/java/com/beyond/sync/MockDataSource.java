package com.beyond.sync;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class MockDataSource<T> implements MultiDataSource<T> {

    static Random random = new Random();

    protected List<T> modifiedData = new ArrayList<T>();

    private String lastSyncKey;

    private String key;

    private String chosenLastSyncKey;

    private Map<String, SyncStamp> syncStampsCache;


    public MockDataSource() {
        super();
    }

    public MockDataSource(String key, String lastSyncKey) {
        this.key = key;
        this.lastSyncKey = lastSyncKey;
    }

    public String getKey() {

        return key;
    }

    public void saveAll(List<T> ts) throws IOException {
        System.out.println(getKey() + ":" + ts);
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
        System.out.println(getKey() + " changed collected " + modifiedData);
        return modifiedData;
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


    public String setChosenKey(String key) {
        return chosenLastSyncKey;
    }

    public String getChosenKey() {
        return chosenLastSyncKey;
    }

    public Map<String, SyncStamp> getSyncStampsCache() {
        return syncStampsCache;
    }

    public void setSyncStampsCache(Map<String, SyncStamp> syncStamps) {
        this.syncStampsCache = syncStamps;
    }

    public void initLastSyncStamps() {

        List<String> names = listAllSyncStampsNames();

        initSyncStampsCache(names);

        sort();

    }

    private void sort() {
        TreeMap<SyncStamp,String> sortMap = new TreeMap<SyncStamp, String>(new Comparator<SyncStamp>() {
            public int compare(SyncStamp o1, SyncStamp o2) {
                if (o1.getLastModifyTime().compareTo(o2.getLastModifyTime())!=0){
                    return -o1.getLastModifyTime().compareTo(o2.getLastModifyTime());
                }else{
                    return -o1.getLastSyncTimeEnd().compareTo(o2.getLastSyncTimeEnd());
                }
            }
        });
        for (String key : syncStampsCache.keySet()) {
            sortMap.put(syncStampsCache.get(key),key);
        }
        syncStampsCache.clear();
        for (SyncStamp syncStamp : sortMap.keySet()) {
            syncStampsCache.put(sortMap.get(syncStamp), syncStamp);
        }
    }

    private List<String> listAllSyncStampsNames() {
        return null;
    }

    private void initSyncStampsCache(List<String> urls) {
        MultiExecuteUtils.blockExecute(null, new MultiExecuteUtils.ParamCallable<String>() {
            public void call(String name) throws Exception {
                SyncStamp syncStamp = getSyncStampFromName(name);
                String key = getKeyFromName();
                syncStampsCache.put(key, syncStamp);
            }
        },null,urls);
    }

    private String getKeyFromName() {
        return null;
    }

    private SyncStamp getSyncStampFromName(String name) {
        return null;
    }


}
