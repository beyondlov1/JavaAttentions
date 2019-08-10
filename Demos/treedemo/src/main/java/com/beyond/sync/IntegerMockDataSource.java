package com.beyond.sync;

public class IntegerMockDataSource extends MockDataSource<Integer> {

    public IntegerMockDataSource(){
        super();
    }

    public IntegerMockDataSource(String key,String lastSyncKey) {
        super(key,lastSyncKey);
        int rand = random.nextInt(5)+1;
        for (int i = 0; i < rand; i++) {
            modifiedData.add(i);
        }
    }

}
