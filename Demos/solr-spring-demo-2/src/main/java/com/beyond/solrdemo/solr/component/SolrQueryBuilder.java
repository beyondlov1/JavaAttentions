package com.beyond.solrdemo.solr.component;

import com.beyond.solrdemo.solr.component.facet.FacetParamSource;
import com.beyond.solrdemo.solr.component.filter.AbstractFilterQueryComp;
import com.beyond.solrdemo.solr.component.query.AbstractQueryComp;
import com.ctc.wstx.util.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author beyondlov1
 * @date 2019/10/18
 */
public class SolrQueryBuilder {

    private Class domainType;

    private SolrQuery query;

    private List<SolrQueryComponent> components = new ArrayList<>();

    private List<FacetParamSource> facetParamSources = new ArrayList<>();


    public SolrQueryBuilder() {
        this(null, null);
    }

    public SolrQueryBuilder(SolrQuery query) {
        this(query, null);
    }

    /**
     * @param domainType 用来映射查询时的输入字段的，并不改变结果处理
     */
    public SolrQueryBuilder(Class domainType) {
        this(null, domainType);
    }

    @SuppressWarnings("ConstantConditions")
    public SolrQueryBuilder(SolrQuery query, Class domainType) {
        if (this.query == null){
            this.query = new SolrQuery("*:*");
        }else {
            this.query = query;
        }
        this.domainType = domainType;
    }


    public SolrQueryBuilder filter(AbstractFilterQueryComp filterQuery){
        components.add(filterQuery);
        return this;
    }

    public SolrQueryBuilder sort(SortComp sortComp){
        components.add(sortComp);
        return this;
    }

    public SolrQueryBuilder query(AbstractQueryComp query){
        components.add(query);
        return this;
    }

    public SolrQueryBuilder add(SolrQueryComponent queryComponent){
        components.add(queryComponent);
        return this;
    }

    public SolrQueryBuilder facet(FacetParamSource facetParamSource) {
        facetParamSources.add(facetParamSource);
        return this;
    }

    public SolrQueryBuilder group(String field){
        query.set("group", true);
        query.set("group.field", field);
        return this;
    }

    public SolrQueryBuilder elevate(List<?> ids){
        if(!CollectionUtils.isEmpty(ids)) {
            query.set("elevateIds", StringUtil.concatEntries(ids, ",", ","));
            query.set("forceElevation", true);
        }
        return this;
    }

    public SolrQueryBuilder set(String name,String...vals){
        query.set(name, vals);
        return this;
    }

    public SolrQueryBuilder page(int offset,int limit){
        query.setStart(offset);
        query.setRows(limit);
        return this;
    }

    public SolrQuery build(){
        if (query==null){
            query = new SolrQuery("*:*");
        }

        for (SolrQueryComponent component : components) {
            if (component instanceof DomainTypeSetter){
                ((DomainTypeSetter) component).setDomainType(this.domainType);
            }
        }

        for (SolrQueryComponent component : components) {
            query = component.chain(query);
        }

        if (!CollectionUtils.isEmpty(facetParamSources)){
            Map<String, Object> param = new LinkedHashMap<>();
            for (FacetParamSource facetParamSource : facetParamSources) {
                param.putAll(facetParamSource.getFacetParam());
            }
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                query.set("json.facet", objectMapper.writeValueAsString(param));
            } catch (JsonProcessingException e) {
                throw new RuntimeException("生成 json.facet 字符串失败");
            }
            query.setFacet(true);
            query.setRows(0);
        }

        return query;
    }

}
