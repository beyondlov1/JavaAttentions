package com.beyond.query.demo.converter;

import com.beyond.query.result.facet.SimpleFacetResult;
import org.apache.solr.client.solrj.response.json.BucketJsonFacet;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author beyondlov1
 * @date 2019/11/07
 */
@Component
public class BucketJsonFacetToSimpleFacetResultConverter implements Converter<BucketJsonFacet, SimpleFacetResult> {
    @Override
    public SimpleFacetResult convert(BucketJsonFacet source) {
        SimpleFacetResult bucketResult = new SimpleFacetResult();
        bucketResult.setVal(source.getVal());
        bucketResult.setCount(source.getCount());
        return bucketResult;
    }
}
