package com.beyond.sync;

import java.io.IOException;
import java.util.List;

public class ExceptionIntegerMockDataSource extends IntegerMockDataSource {

    public ExceptionIntegerMockDataSource() {
        int rand = random.nextInt(5)+1;
        for (int i = 0; i < rand; i++) {
            list.add(i);
        }
    }

    @Override
    public List<Integer> getChangedData(SyncStamp syncStamp) throws IOException {
        throw new RuntimeException("exception:"+getKey());
    }
}
