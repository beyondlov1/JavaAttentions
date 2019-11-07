package com.beyond.solrdemo.converter;

import com.beyond.solrdemo.solr.result.PriceFacetResult;
import org.apache.solr.client.solrj.response.json.BucketJsonFacet;
import org.springframework.core.convert.converter.Converter;

/**
 * @author chenshipeng
 * @date 2019/11/07
 */
public class BucketJsonFacetToPriceFacetResultConverter implements Converter<BucketJsonFacet, PriceFacetResult> {
    @Override
    public PriceFacetResult convert(BucketJsonFacet source) {
        PriceFacetResult bucketResult = new PriceFacetResult();
        bucketResult.setVal(source.getVal());
        bucketResult.setCount(source.getCount());
        return bucketResult;
    }
}
