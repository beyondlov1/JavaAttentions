package com.beyond.query.es;

import com.beyond.query.Page;
import com.beyond.query.ResultContainer;
import com.beyond.query.ResultHandler;
import com.google.gson.JsonObject;
import io.searchbox.core.SearchResult;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenshipeng
 * @date 2019/11/26
 */
public class EsResultContainer implements ResultContainer<SearchResult> {

    private List<ResultHandler> resultHandlers = new ArrayList<>();

    private SearchResult response;

    @Override
    public void addResultHandler(ResultHandler resultHandler) {
        resultHandlers.add(resultHandler);
    }

    @Override
    public <T> List<T> getQueryResult(Class<T> clazz) {
        return response.getSourceAsObjectList(clazz, false);
    }

    @Override
    public <T> Page<T> getQueryResultPage(Class<T> clazz, Pageable pageable) {
        Page<T> page = new Page<>();
        List<T> content = getQueryResult(clazz);
        JsonObject jsonObject = response.getJsonObject();
        long count = jsonObject.get("hits").getAsJsonObject().get("total").getAsJsonObject().get("value").getAsLong();
        page.setQueryNum(count);
        page.setCurrPage(pageable.getPageNumber());
        page.setTotalPage((int) (count%pageable.getPageSize() == 0?count/pageable.getPageSize():count/pageable.getPageSize()+1));
        page.setContent(content);
        return page;
    }

    @Override
    public SearchResult getResponse() {
        return response;
    }

    @Override
    public void setResponse(SearchResult response) {
        this.response = response;
    }
}
