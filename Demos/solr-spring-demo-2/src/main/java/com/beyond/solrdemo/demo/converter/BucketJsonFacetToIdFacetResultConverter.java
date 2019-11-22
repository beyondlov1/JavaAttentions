package com.beyond.solrdemo.demo.converter;

import com.beyond.solrdemo.demo.result.facet.IdFacetResult;
import org.apache.solr.client.solrj.response.json.BucketJsonFacet;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author beyondlov1
 * @date 2019/11/07
 */
@Component
public class BucketJsonFacetToIdFacetResultConverter implements Converter<BucketJsonFacet, IdFacetResult> {
    @Override
    public IdFacetResult convert(BucketJsonFacet source) {
        IdFacetResult bucketResult = new IdFacetResult();
        bucketResult.setVal(source.getVal());
        bucketResult.setCount(source.getCount());
        bucketResult.setSumAmount((Double) source.getStatFacetValue("sumAmount"));
        bucketResult.setStoreCount(Long.valueOf(String.valueOf(source.getStatFacetValue("storeCount"))));
        return bucketResult;
    }
}
