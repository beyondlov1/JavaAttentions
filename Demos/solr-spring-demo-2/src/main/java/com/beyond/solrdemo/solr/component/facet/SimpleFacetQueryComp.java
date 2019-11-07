package com.beyond.solrdemo.solr.component.facet;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author beyondlov1
 * @date 2019/11/07
 */
public class SimpleFacetQueryComp implements FacetParamSource{

    private final String fieldName;

    public SimpleFacetQueryComp(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public Map<String, Object> getFacetParam() {
        Map<String, Object> facetParams = new LinkedHashMap<>();
        facetParams.put("type", "terms");
        facetParams.put("field", fieldName);

        HashMap<String, Object> sorts = new LinkedHashMap<>();
        sorts.put("count", "desc");
        facetParams.put("sort", sorts);

        HashMap<String, Object> facet = new LinkedHashMap<>();
        facetParams.put("facet", facet);

        Map<String, Object> param = new LinkedHashMap<>();
        param.put(fieldName+"_facet", facetParams);
        return param;
    }
}
