package com.beyond.demo.playground;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread(new Service(countDownLatch,i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        doNext();
    }

    private static void doNext() {
        System.out.println("do next");
    }


}

class Service implements Runnable{

    private CountDownLatch countDownLatch;

    private int data;

    public Service(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public Service(CountDownLatch countDownLatch, int data) {
        this.countDownLatch = countDownLatch;
        this.data = data;
    }

    @Override
    public void run() {
        try{
            doService();
        }finally {
            countDownLatch.countDown();
        }
    }

    private void doService() {
        try {
            System.out.println("do service start"+data);
            Thread.sleep(1000*new Random().nextInt(10));
            System.out.println("do service end"+data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}