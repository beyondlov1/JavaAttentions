package com.beyond.solrdemo.converter;

import com.beyond.solrdemo.solr.result.facet.IdFacetResult;
import org.apache.solr.client.solrj.response.json.BucketJsonFacet;
import org.springframework.core.convert.converter.Converter;

/**
 * @author beyondlov1
 * @date 2019/11/07
 */
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
