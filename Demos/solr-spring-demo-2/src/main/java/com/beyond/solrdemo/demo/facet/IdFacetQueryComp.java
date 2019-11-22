package com.beyond.solrdemo.demo.facet;

import com.beyond.solrdemo.solr.component.facet.FacetParamSource;
import lombok.Data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author chenshipeng
 * @date 2019/11/06
 */
@Data
public class IdFacetQueryComp implements FacetParamSource {

    @Override
    public Map<String,Object> getFacetParam(){
        String fieldName = "id";
        Map<String, Object> facetParams = new LinkedHashMap<>();
        facetParams.put("type", "terms");
        facetParams.put("field", fieldName);
        facetParams.put("limit", 1000);

        HashMap<String, Object> sorts = new LinkedHashMap<>();
        sorts.put("sumAmount", "desc");
        sorts.put("storeCount", "desc");
        sorts.put("count", "desc");
        facetParams.put("sort", sorts);

        HashMap<String, Object> facet = new LinkedHashMap<>();
        facet.put("sumAmount", "sum(price)");
        facet.put("storeCount", "unique(id)");
        facetParams.put("facet", facet);

        Map<String, Object> param = new LinkedHashMap<>();
        param.put(fieldName+"_facet", facetParams);
        return param;
    }

}
