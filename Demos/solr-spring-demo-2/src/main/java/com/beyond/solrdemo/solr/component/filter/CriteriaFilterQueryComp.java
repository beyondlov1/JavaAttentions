package com.beyond.solrdemo.solr.component.filter;

import com.beyond.solrdemo.solr.component.DomainTypeSetter;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.data.solr.core.DefaultQueryParser;
import org.springframework.data.solr.core.mapping.SimpleSolrMappingContext;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.lang.Nullable;

/**
 * @author beyondlov1
 * @date 2019/11/11
 */
public class CriteriaFilterQueryComp extends AbstractFilterQueryComp implements DomainTypeSetter {

    private Criteria criteria;

    private Class domainType;

    private static DefaultQueryParser defaultQueryParser = new DefaultQueryParser(new SimpleSolrMappingContext());

    public CriteriaFilterQueryComp(Criteria criteria) {
        this.criteria = criteria;
    }

    public CriteriaFilterQueryComp(Criteria criteria, @Nullable Class domainType) {
        this.criteria = criteria;
        this.domainType = domainType;
    }

    @Override
    protected void init(SolrQuery query) {
        expression = defaultQueryParser.createQueryStringFromNode(criteria, domainType);
    }

    @Override
    public void setDomainType(Class domainType) {
        this.domainType = domainType;
    }
}
