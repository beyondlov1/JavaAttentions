package com.beyond.query.demo.facet;

import com.beyond.query.solr.component.facet.FacetParamSource;
import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author beyondlov1
 * @date 2019/11/06
 */
@Data
public class IdFacetQueryComp implements FacetParamSource {

    private Pageable pageable;

    public IdFacetQueryComp() {
    }

    public IdFacetQueryComp(Pageable pageable) {
        this.pageable = pageable;
    }

    @Override
    public Map<String,Object> getFacetParam(){
        String fieldName = "id";
        Map<String, Object> facetParams = new LinkedHashMap<>();
        facetParams.put("type", "terms");
        facetParams.put("field", fieldName);
        if (pageable!=null){
            facetParams.put("offset", pageable.getOffset());
            facetParams.put("limit", pageable.getPageSize());
        }else {
            facetParams.put("offset", 0);
            facetParams.put("limit", 10000);
        }

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
