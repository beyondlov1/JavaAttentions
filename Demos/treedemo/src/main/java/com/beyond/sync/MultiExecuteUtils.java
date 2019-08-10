package com.beyond.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MultiExecuteUtils {
    @SuppressWarnings("unchecked")
    public static  <V> void blockExecute(ExecutorService executorService,
                                         final ParamCallable<V> callable,
                                         ParamCallable<V> exceptionHandler,
                                         List<V> targetList){
        List<Future<?>> futures = new ArrayList<Future<?>>();

        for (final V object : targetList) {
            Future<?> future = executorService.submit(new Callable<Object>() {
                public Object call() throws Exception {
                    try {
                        callable.call(object);
                    }catch (Exception e){
                        BlockExecuteException exception = new BlockExecuteException();
                        exception.setObject(object);
                        exception.initCause(e);
                        throw exception;
                    }
                    return object;
                }
            });
            futures.add(future);
        }
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                if (exceptionHandler == null){
                    return;
                }
                if (e.getCause() instanceof BlockExecuteException){
                    try {
                        exceptionHandler.call((V) ((BlockExecuteException)(e.getCause())).getObject());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    public interface ParamCallable<V>{
        void call(V singleExecutor) throws Exception;
    }

    public static class BlockExecuteException extends Exception{

        private Object object;

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }
    }
}
