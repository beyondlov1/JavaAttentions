package com.beyond.solrdemo.demo.result.facet;

import com.beyond.solrdemo.solr.result.facet.SimpleFacetResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author beyondlov1
 * @date 2019/11/07
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class IdFacetResult extends SimpleFacetResult {
    private Double sumAmount;
    private Number storeCount;
}
