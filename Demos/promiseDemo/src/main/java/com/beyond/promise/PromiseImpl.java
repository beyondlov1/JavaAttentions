package com.beyond.promise;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author beyondlov1
 * @date 2019/05/29
 */
public class PromiseImpl implements Promise {

    List<ArgCallable> successRunnables = new LinkedList<>();
    List<ArgCallable> failRunnables = new LinkedList<>();

    Callable asynTask;

    Object argument;

    public PromiseImpl(Callable runnable){
        this.asynTask = runnable;
    }

    @Override
    public Promise then(ArgCallable function) {
        successRunnables.add(function);
        return this;
    }

    @Override
    public Promise catchException(ArgCallable function) {
        failRunnables.add(function);
        return this;
    }

    @Override
    public Object call() throws Exception {
        Object result = null;
        try {
            result = asynTask.call();
            for (ArgCallable successRunnable : successRunnables) {
                successRunnable.setArgument(result);
                result = successRunnable.call();
            }
        }catch (Exception e){
            for (ArgCallable failRunnable : failRunnables) {
                failRunnable.setArgument(e);
                failRunnable.call();
            }
        }
        return result;
    }

    @Override
    public void setArgument(Object o) {
        argument = o;
    }
}
