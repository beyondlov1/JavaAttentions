package com.beyond.solrdemo.solr.component.filter;

import com.ctc.wstx.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author chenshipeng
 * @date 2019/11/19
 */
@Slf4j
public class SimpleInFilterQueryComp extends AbstractFilterQueryComp {

    private List<String> values;

    public SimpleInFilterQueryComp(String field, List<String> values) {
        this.field = field;
        this.values = values;
    }

    @Override
    protected void init(SolrQuery query) {
        if (CollectionUtils.isEmpty(values)){
            log.info("field:"+field+"; values is empty, this filter query will be abort;");
            return;
        }
        filterExpr = "(" +
                StringUtil.concatEntries(values, " ", " ") +
                ")";
    }
}
