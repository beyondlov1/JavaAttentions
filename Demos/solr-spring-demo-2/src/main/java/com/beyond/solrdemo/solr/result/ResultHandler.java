package com.beyond.solrdemo.solr.result;

/**
 * @author beyondlov1
 * @date 2019/11/22
 */
public interface ResultHandler<S,T,M> {
    T handle(S s,M m);
}
