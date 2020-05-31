package com.beyond.query;

import org.springframework.util.CollectionUtils;

import java.util.List;

public interface ResultHandlerContainer<S, T, M> extends ResultHandler<S, T, M> {

    List<ResultHandler> getResultHandlers();

    @SuppressWarnings("unchecked")
    @Override
    default T handle(S s, M m) {
        Object result = s;
        if (CollectionUtils.isEmpty(getResultHandlers())) {
            throw new RuntimeException("result handlers is empty");
        }
        for (ResultHandler resultHandler : getResultHandlers()) {
            result = resultHandler.handle(result, m);
        }
        return (T) result;
    }
}
