package com.beyond.sync;

import java.io.IOException;
import java.util.List;

public class ExceptionIntegerMockDataSource extends IntegerMockDataSource {

    public ExceptionIntegerMockDataSource(){
        super();
    }

    public ExceptionIntegerMockDataSource(String key,String lastSyncKey) {
        super(key,lastSyncKey);
    }

    @Override
    public List<Integer> getChangedData(SyncStamp syncStamp) throws IOException {
        throw new RuntimeException("exception:"+getKey());
    }
}
