package com.beyond.query;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author chenshipeng
 * @date 2019/12/02
 */
public class QueryComponentUtils {
    public static <Query> Query chain(List<QueryComponent<Query>> queryComponents,Query query){
        if (CollectionUtils.isEmpty(queryComponents)){
            return query;
        }
        for (QueryComponent<Query> queryComponent : queryComponents) {
            query = queryComponent.chain(query);
        }
        return query;
    }
}
