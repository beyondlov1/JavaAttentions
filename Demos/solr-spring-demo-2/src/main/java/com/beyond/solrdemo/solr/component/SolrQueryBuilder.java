package com.beyond.solrdemo.solr.component;

import com.beyond.solrdemo.solr.component.filter.FilterQueryComp;
import com.ctc.wstx.util.StringUtil;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author beyondlov1
 * @date 2019/10/18
 */
public class SolrQueryBuilder {

    private SolrQuery query;

    private Set<SolrQueryComponent> components = new HashSet<>();

    public SolrQueryBuilder() {
        this.query = new SolrQuery("*:*");
    }

    public SolrQueryBuilder(SolrQuery query) {
        this.query = query;
    }

    public SolrQueryBuilder filter(FilterQueryComp filterQuery){
        components.add(filterQuery);
        return this;
    }

    public SolrQueryBuilder sort(SortComp sortComp){
        components.add(sortComp);
        return this;
    }

    public SolrQueryBuilder query(QueryComp query){
        components.add(query);
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
            component.chain(query);
        }
        return query;
    }
}
