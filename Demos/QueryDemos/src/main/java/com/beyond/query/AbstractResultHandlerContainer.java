package com.beyond.query;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author beyondlov1
 * @date 2019/11/22
 */
public abstract class AbstractResultHandlerContainer<S,T,M> implements ResultHandlerContainer<S,T,M> {

    @SuppressWarnings("WeakerAccess")
    protected List<ResultHandler> resultHandlers;

    @SuppressWarnings("unchecked")
    @Override
    public T handle(S s, M m) {
        Object result = s;
        if (CollectionUtils.isEmpty(resultHandlers)) {
            throw new RuntimeException("result handlers is empty");
        }
        for (ResultHandler resultHandler : resultHandlers) {
            result = resultHandler.handle(result, m);
        }
        return (T) result;
    }

    protected abstract void initHandlers();
}
