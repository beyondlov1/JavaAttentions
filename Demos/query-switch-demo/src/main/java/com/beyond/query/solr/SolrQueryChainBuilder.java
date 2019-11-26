package com.beyond.query.solr;

import com.beyond.query.QueryChainBuilder;
import com.beyond.query.QueryComponent;
import com.beyond.query.solr.component.DomainTypeSetter;
import com.beyond.query.solr.component.SortComp;
import com.beyond.query.solr.component.facet.FacetParamSource;
import com.beyond.query.solr.component.filter.AbstractFilterQueryComp;
import com.beyond.query.solr.component.query.AbstractQueryComp;
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
public class SolrQueryChainBuilder implements QueryChainBuilder<SolrQuery> {

    private Class domainType;

    private SolrQuery query;

    private List<QueryComponent<SolrQuery>> components = new ArrayList<>();

    private List<FacetParamSource> facetParamSources = new ArrayList<>();


    public SolrQueryChainBuilder() {
        this(null, null);
    }

    public SolrQueryChainBuilder(SolrQuery query) {
        this(query, null);
    }

    /**
     * @param domainType 用来映射查询时的输入字段的，并不改变结果处理
     */
    public SolrQueryChainBuilder(Class domainType) {
        this(null, domainType);
    }

    @SuppressWarnings("ConstantConditions")
    public SolrQueryChainBuilder(SolrQuery query, Class domainType) {
        if (this.query == null){
            this.query = new SolrQuery("*:*");
        }else {
            this.query = query;
        }
        this.domainType = domainType;
    }


    public SolrQueryChainBuilder filter(AbstractFilterQueryComp filterQuery){
        components.add(filterQuery);
        return this;
    }

    public SolrQueryChainBuilder sort(SortComp sortComp){
        components.add(sortComp);
        return this;
    }

    public SolrQueryChainBuilder query(AbstractQueryComp query){
        components.add(query);
        return this;
    }

    public SolrQueryChainBuilder facet(FacetParamSource facetParamSource) {
        facetParamSources.add(facetParamSource);
        return this;
    }

    public SolrQueryChainBuilder group(String field){
        query.set("group", true);
        query.set("group.field", field);
        return this;
    }

    public SolrQueryChainBuilder elevate(List<?> ids){
        if(!CollectionUtils.isEmpty(ids)) {
            query.set("elevateIds", StringUtil.concatEntries(ids, ",", ","));
            query.set("forceElevation", true);
        }
        return this;
    }

    public SolrQueryChainBuilder set(String name, String...vals){
        query.set(name, vals);
        return this;
    }

    public SolrQueryChainBuilder page(int offset, int limit){
        query.setStart(offset);
        query.setRows(limit);
        return this;
    }

    @Override
    public QueryChainBuilder<SolrQuery> add(QueryComponent<SolrQuery> queryComponent) {
        components.add(queryComponent);
        return this;
    }

    @Override
    public SolrQuery build(){
        if (query==null){
            query = new SolrQuery("*:*");
        }

        for (QueryComponent<SolrQuery> component : components) {
            if (component instanceof DomainTypeSetter){
                ((DomainTypeSetter) component).setDomainType(this.domainType);
            }
        }

        for (QueryComponent<SolrQuery> component : components) {
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
