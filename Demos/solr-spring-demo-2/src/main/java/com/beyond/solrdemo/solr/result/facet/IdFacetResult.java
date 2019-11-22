package com.beyond.solrdemo.solr.result.facet;

import lombok.Data;

/**
 * @author beyondlov1
 * @date 2019/11/07
 */
@Data
public class IdFacetResult extends SimpleFacetResult {
    private Double sumAmount;
    private Number storeCount;
}
