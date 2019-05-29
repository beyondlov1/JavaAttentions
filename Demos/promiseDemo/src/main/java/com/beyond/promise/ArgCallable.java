package com.beyond.promise;

import java.util.concurrent.Callable;

public interface ArgCallable<T,V> extends Callable<V> {
    void setArgument(T t);
}
