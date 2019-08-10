package com.beyond.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ScheduleCommonUtils {
    public static ScheduledExecutorService getScheduleExecutorService(){
        return Executors.newScheduledThreadPool(5);
    }
}
