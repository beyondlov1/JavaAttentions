package com.beyond.solrdemo.solr.component.filter;

import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Field;

import java.util.Collection;
import java.util.Set;

/**
 * @author beyondlov1
 * @date 2019/11/11
 */
public class CriteriaFilterQueryComp extends FilterQueryComp {
    private Criteria criteria;

    public CriteriaFilterQueryComp(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    protected void init() {
        super.init();
        Field field = criteria.getField();
        Set<Criteria.Predicate> predicates = criteria.getPredicates();
        Collection<Criteria> siblings = criteria.getSiblings();
    }
}
