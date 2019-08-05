package com.beyond.sync;

public class IntegerMockDataSource extends MockDataSource<Integer> {

    public IntegerMockDataSource() {
        int rand = random.nextInt(5)+1;
        for (int i = 0; i < rand; i++) {
            list.add(i);
        }
    }

}
