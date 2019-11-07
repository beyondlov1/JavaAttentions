package com.beyond.solrdemo.solr.result;

import lombok.Data;

/**
 * @author chenshipeng
 * @date 2019/11/07
 */
@Data
public class BaseFacetResult {
    private Object val;
    private Long count;
}
