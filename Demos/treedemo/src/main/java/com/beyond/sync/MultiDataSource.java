package com.beyond.sync;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface MultiDataSource<T>  extends DataSource<T>{
    String setChosenLastSyncKey();
    String getChosenLastSyncKey();
    Map<String,SyncStamp> getAllLastSycnStamp();
}
