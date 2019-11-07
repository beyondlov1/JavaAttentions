package com.beyond.solrdemo.solr.result;

import lombok.Data;

/**
 * @author chenshipeng
 * @date 2019/11/07
 */
@Data
public class IdFacetResult extends BaseFacetResult{
    private Double sumAmount;
    private Number storeCount;
}
