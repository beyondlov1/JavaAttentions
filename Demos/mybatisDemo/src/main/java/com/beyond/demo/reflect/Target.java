package com.beyond.demo.reflect;

public class Target implements TargetInterface {

    private Callback callback = Main.RedoableFactory.getInstance(new MyCallback());

    public void test() {
        System.out.println("test method running");
        callback.onSuccess("testName");
        System.out.println("test method complete");
    }

    public void test2() {

    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    interface Callback {
        void onSuccess(String name);

        void onFail();
    }

    static class MyCallback implements Callback {

        public void onSuccess(String name) {
            System.out.println("name:"+name);
        }

        public void onFail() {

        }
    }
}
