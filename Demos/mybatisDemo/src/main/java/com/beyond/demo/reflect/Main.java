package com.beyond.demo.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        Target target = new Target();
        target.test();

    }

    static class RedoableFactory{
        public static <T> T getInstance(T target) {
            return  (T)Proxy.newProxyInstance(target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(),
                    new RedoRecordProxy<T>(target));
        }
    }

    static class RedoRecordProxy<T> implements InvocationHandler {

        private final T t;

        public RedoRecordProxy(T t) {
            this.t = t;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result = null;
            try {
                System.out.println("prepare redo params:"+ Arrays.toString(args));
                result = method.invoke(t, args);
                System.out.println("delete redo message:params:"+ Arrays.toString(args));
            }catch (Exception e){
                System.out.println("record redo message");
            }
            return result;
        }
    }
}
