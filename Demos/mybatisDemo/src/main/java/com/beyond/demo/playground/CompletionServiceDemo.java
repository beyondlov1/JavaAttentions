package com.beyond.demo.playground;

import java.util.Random;
import java.util.concurrent.*;

public class CompletionServiceDemo {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                10, 20,
                5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(), Executors.defaultThreadFactory());
        CompletionService<Integer> executorCompletionService = new ExecutorCompletionService<Integer>(executorService);
        for (int i = 0; i < 100; i++) {
            System.out.println("produce start");
            executorCompletionService.submit(new MyCallable(i));
            System.out.println("produce end");
        }

        Future<Integer> future = null;
        try {
            for (int i = 0; i < 100; i++) {
                future = executorCompletionService.take();
                Integer integer = future.get();
                System.out.println(integer);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ((ThreadPoolExecutor) executorService).setCorePoolSize(0);
    }
}

class MyCallable implements Callable<Integer> {

    private int data;

    public MyCallable(int data) {
        this.data = data;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(100*new Random().nextInt(10) );
        return data;
    }
}
