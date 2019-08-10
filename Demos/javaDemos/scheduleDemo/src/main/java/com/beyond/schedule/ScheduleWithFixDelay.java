package com.beyond.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 在执行完成之后才进行下一次的计时
 * 也就是要考虑执行时长
 */
public class ScheduleWithFixDelay {
    public static void main(String[] args) {
        ScheduledExecutorService scheduleExecutorService = ScheduleCommonUtils.getScheduleExecutorService();
        scheduleExecutorService.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(format.format(new Date()));
            }
        },0,3000, TimeUnit.MILLISECONDS);
    }
}
