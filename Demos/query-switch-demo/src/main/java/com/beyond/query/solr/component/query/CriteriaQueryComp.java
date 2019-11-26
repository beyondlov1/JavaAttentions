package com.beyond.query.solr.component.query;

import com.beyond.query.solr.component.DomainTypeSetter;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.data.solr.core.DefaultQueryParser;
import org.springframework.data.solr.core.mapping.SimpleSolrMappingContext;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.lang.Nullable;

/**
 * @author beyondlov1
 * @date 2019/11/11
 */
public class CriteriaQueryComp extends AbstractQueryComp implements DomainTypeSetter {

    private Criteria criteria;

    private Class domainType;

    private static DefaultQueryParser defaultQueryParser = new DefaultQueryParser(new SimpleSolrMappingContext());

    public CriteriaQueryComp(Criteria criteria) {
        this.criteria = criteria;
    }
    public CriteriaQueryComp(Criteria criteria, @Nullable Class domainType) {
        this.criteria = criteria;
        this.domainType = domainType;
    }

    @Override
    protected void init(SolrQuery solrQuery) {
        expression = defaultQueryParser.createQueryStringFromNode(criteria, domainType);
    }

    @Override
    public void setDomainType(Class domainType) {
        this.domainType = domainType;
    }
}
