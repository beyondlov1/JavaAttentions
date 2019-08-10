package com.beyond.schedule;

import javafx.scene.input.DataFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 以固定频率进行, 不管运行了多久都是尽量赶原来的节奏, 也就是如果超了时长就完成后立即执行.
 * 不考虑执行时长
 */
public class ScheduleAtFixRate {
    public static void main(String[] args) {
        ScheduledExecutorService scheduleExecutorService = ScheduleCommonUtils.getScheduleExecutorService();
        scheduleExecutorService.scheduleAtFixedRate(new Runnable() {
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                try {
                    Thread.sleep(3000+new Random().nextInt(2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(format.format(new Date()));
            }
        },0,3000, TimeUnit.MILLISECONDS);
    }
}
