package com.beyond.query.es.component;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;

/**
 * @author chenshipeng
 * @date 2019/11/26
 */
public class PriceRangeEsQueryComp extends AbstractEsQueryComp {

    private BigDecimal start;
    private BigDecimal end;

    public PriceRangeEsQueryComp(@Nullable BigDecimal start, @Nullable BigDecimal end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected QueryBuilder getQueryBuilder() {
        return QueryBuilders.rangeQuery("price").gte(start).lt(end);
    }
}
