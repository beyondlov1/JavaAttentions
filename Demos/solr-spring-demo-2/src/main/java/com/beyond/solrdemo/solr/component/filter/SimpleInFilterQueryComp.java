package com.ysb.service.commodity.solr.component.filter;

import com.ctc.wstx.util.StringUtil;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author chenshipeng
 * @date 2019/11/19
 */
public class SimpleInFilterQueryComp extends AbstractFilterQueryComp {

    private List<String> values;

    public SimpleInFilterQueryComp(String field,List<String> values) {
        this.field = field;
        this.values = values;
    }

    @Override
    protected void init(SolrQuery query) {
        if (CollectionUtils.isEmpty(values)){
            filterExpr = "()";
            return;
        }
        filterExpr = "(" +
                StringUtil.concatEntries(values, " ", " ") +
                ")";
    }
}
