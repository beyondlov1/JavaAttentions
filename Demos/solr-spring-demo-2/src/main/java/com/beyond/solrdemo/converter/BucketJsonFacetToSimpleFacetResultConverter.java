package com.beyond.solrdemo.converter;

import com.beyond.solrdemo.solr.result.SimpleFacetResult;
import org.apache.solr.client.solrj.response.json.BucketJsonFacet;
import org.springframework.core.convert.converter.Converter;

/**
 * @author beyondlov1
 * @date 2019/11/07
 */
public class BucketJsonFacetToSimpleFacetResultConverter implements Converter<BucketJsonFacet, SimpleFacetResult> {
    @Override
    public SimpleFacetResult convert(BucketJsonFacet source) {
        SimpleFacetResult bucketResult = new SimpleFacetResult();
        bucketResult.setVal(source.getVal());
        bucketResult.setCount(source.getCount());
        return bucketResult;
    }
}
