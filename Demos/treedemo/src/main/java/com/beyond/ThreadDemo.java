package com.beyond;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10,20,
                10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(80));

        final AtomicInteger integer = new AtomicInteger(0);
        for (int i = 0; i < 100; i++) {
            final int finalI = i;
            threadPoolExecutor.execute(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (integer.getAndAdd(1)%10==0){
                        System.out.println();
                    }

                    System.out.println(finalI);
                }
            });
        }
    }
}
