package com.beyond.solrdemo.solr.result;

import lombok.Data;

import java.util.Collections;
import java.util.Map;

/**
 * @author beyondlov1
 * @date 2019/11/23
 */
@Data
public class BPage<T> {
    private long queryNum;
    private int currPage;
    private int totalPage;
    private Map<Object,T> content = Collections.emptyMap();
}
