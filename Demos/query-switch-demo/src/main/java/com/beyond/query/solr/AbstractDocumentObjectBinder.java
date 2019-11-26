package com.beyond.query.solr;

import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author beyondlov1
 * @date 2019/11/01
 */
public abstract class AbstractDocumentObjectBinder<M> extends DocumentObjectBinder {

    @Override
    public <T> List<T> getBeans(Class<T> clazz, SolrDocumentList solrDocList) {
        List<T> result = new ArrayList<>(solrDocList.size());
        for (SolrDocument solrDoc : solrDocList) {
            result.add(this.getBean(clazz, solrDoc));
        }
        return result;
    }

    @Override
    public <T> T getBean(Class<T> clazz, SolrDocument solrDoc) {
        if (clazz().isAssignableFrom(clazz)) {
            M m = getBeanInternal(solrDoc);
            return clazz.cast(m);
        }
        return super.getBean(clazz, solrDoc);
    }

    protected abstract M getBeanInternal(SolrDocument solrDoc);

    @SuppressWarnings("unchecked")
    protected Class<M> clazz(){
        ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = type.getActualTypeArguments();
        return (Class<M>) actualTypeArguments[0];
    }
}
