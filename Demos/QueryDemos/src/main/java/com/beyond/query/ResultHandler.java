package com.beyond.query;

public interface ResultHandler<S,T,M> {
    T handle(S s, M m);
}
