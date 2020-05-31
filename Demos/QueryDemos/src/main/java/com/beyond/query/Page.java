package com.beyond.query;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author beyondlov1
 * @date 2019/11/23
 */
public class Page<T> {
    private long queryNum;
    private int currPage;
    private int totalPage;
    private Map<Object,T> contentMap = Collections.emptyMap();
    private List<T> content = Collections.emptyList();

    public long getQueryNum() {
        return queryNum;
    }

    public void setQueryNum(long queryNum) {
        this.queryNum = queryNum;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Map<Object, T> getContentMap() {
        return contentMap;
    }

    public void setContentMap(Map<Object, T> contentMap) {
        this.contentMap = contentMap;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
