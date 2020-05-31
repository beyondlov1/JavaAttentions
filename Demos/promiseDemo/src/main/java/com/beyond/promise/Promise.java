package com.beyond.promise;


import java.util.concurrent.Callable;

/**
 * @author beyondlov1
 * @date 2019/05/29
 */
public interface Promise extends ArgCallable{
    Promise then(ArgCallable function);
    Promise catchException(ArgCallable function);
}
