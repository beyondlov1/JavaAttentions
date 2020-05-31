package com.beyond.promise;

import java.util.concurrent.Callable;

/**
 * @author beyondlov1
 * @date 2019/05/29
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Promise promise = new PromiseImpl(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("asynTask running");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("asynTask complete");
                return "ccc";
            }
        });
        promise.then(new ArgCallable() {
            Object object;

            @Override
            public void setArgument(Object o) {
                object = o;
            }

            @Override
            public Object call() throws Exception {
                System.out.println(object);
                return "bbbb";
            }
        }).then(new ArgCallable() {
            Object object;

            @Override
            public void setArgument(Object o) {
                object = o;
            }

            @Override
            public Object call() throws Exception {
                System.out.println(object);
                return "eeeee";
            }
        });
        Object call = promise.call();
        System.out.println(call);
    }
}
