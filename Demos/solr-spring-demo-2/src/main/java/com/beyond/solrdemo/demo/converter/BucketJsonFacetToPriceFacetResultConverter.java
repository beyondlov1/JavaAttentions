package com.beyond.solrdemo.demo.converter;

import com.beyond.solrdemo.demo.result.facet.PriceFacetResult;
import org.apache.solr.client.solrj.response.json.BucketJsonFacet;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author beyondlov1
 * @date 2019/11/07
 */
@Component
public class BucketJsonFacetToPriceFacetResultConverter implements Converter<BucketJsonFacet, PriceFacetResult> {
    @Override
    public PriceFacetResult convert(BucketJsonFacet source) {
        PriceFacetResult bucketResult = new PriceFacetResult();
        bucketResult.setVal(source.getVal());
        bucketResult.setCount(source.getCount());
        return bucketResult;
    }
}
