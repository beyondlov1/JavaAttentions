package com.beyond.solrdemo.solr.component.filter;

import com.ctc.wstx.util.StringUtil;
import lombok.Data;
import org.apache.solr.client.solrj.SolrQuery;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 地区过滤
 *
 * @author beyondlov1
 * @date 2019/11/01
 */
@Data
public class AreaFilterQueryComp extends AbstractFilterQueryComp {

    private final int areaCode;

    public AreaFilterQueryComp(String field, int areaCode) {
        this.field = field;
        this.areaCode = areaCode;
    }

    @Override
    protected void init(SolrQuery query) {
        List<String> areaCodeSubStrings = new LinkedList<>();
        String areaCodeStr = String.valueOf(areaCode);
        areaCodeStr = "-1".equals(areaCodeStr) ? "0" : areaCodeStr;
        List<Integer> lens = Arrays.asList(2, 4, 6, 9);
        for (Integer len : lens) {
            if (areaCodeStr.length() >= len) {
                areaCodeSubStrings.add(areaCodeStr.substring(0, len));
            }
        }
        filterExpr = StringUtil.concatEntries(areaCodeSubStrings, ",", ",");
    }

}
