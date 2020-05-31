package com.beyond.query.es.component;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenshipeng
 * @date 2019/11/26
 */
public class SimpleEsQueryComp extends AbstractEsQueryComp {

    private String field;

    private Object value;

    public SimpleEsQueryComp(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    @Override
    protected QueryBuilder getQueryBuilder() {
        return QueryBuilders.termQuery(field, value);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(null);
        System.out.println(CollectionUtils.isEmpty(list));
    }
}
