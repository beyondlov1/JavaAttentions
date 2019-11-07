package com.beyond.solrdemo.solr.result;

/**
 * @author chenshipeng
 * @date 2019/11/07
 */
public interface BucketResult {

    Object getVal();

    void setVal(Object val);

    Long getCount();

    void setCount(Long count);
}
