package com.beyond.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 单次执行
 */
public class Schedule {
    public static void main(String[] args) {
        ScheduledExecutorService scheduleExecutorService = ScheduleCommonUtils.getScheduleExecutorService();
        scheduleExecutorService.schedule(new Runnable() {
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(format.format(new Date()));
            }
        },3000, TimeUnit.MILLISECONDS);
    }
}
