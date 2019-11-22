package com.beyond.solrdemo.solr.result;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.json.BucketBasedJsonFacet;
import org.apache.solr.client.solrj.response.json.BucketJsonFacet;
import org.apache.solr.client.solrj.response.json.NestableJsonFacet;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * @author beyondlov1
 * @date 2019/11/07
 */
public class ResultContainer {

    private static final String FACET_SUFFIX = "_facet";

    private GenericConversionService conversionService = new GenericConversionService();

    private QueryResponse response;

    public ResultContainer() {
    }

    public ResultContainer(QueryResponse response) {
        this.response = response;
    }

    /** --------------------- facet --------------------- */

    /**
     * {facetName:{facetVal:facetData}}
     */
    @SuppressWarnings("unchecked")
    public Map<String, Map<Object, Object>> getFacetResults(Class commonClass) {

        Map<String, Map<Object, Object>> results = new HashMap<>();
        NestableJsonFacet jsonFacetingResponse = response.getJsonFacetingResponse();
        Set<String> bucketBasedFacetNames = jsonFacetingResponse.getBucketBasedFacetNames();
        for (String bucketBasedFacetName : bucketBasedFacetNames) {
            results.put(bucketBasedFacetName, getFacetResultByFacetName(bucketBasedFacetName, commonClass));
        }

        return results;
    }

    public Map<String, Map<?, ?>> getFacetResults(Map<String, Class<?>> classMap) {

        Map<String, Map<?, ?>> results = new HashMap<>();
        NestableJsonFacet jsonFacetingResponse = response.getJsonFacetingResponse();
        Set<String> bucketBasedFacetNames = jsonFacetingResponse.getBucketBasedFacetNames();
        for (String bucketBasedFacetName : bucketBasedFacetNames) {
            results.put(bucketBasedFacetName, getFacetResultByFacetName(bucketBasedFacetName, classMap.get(bucketBasedFacetName)));
        }

        return results;
    }

    public <T> Map<Object, T> getFacetResultByFieldName(String fieldName, Class<T> clazz) {
        String bucketBasedFacetName = fieldName + FACET_SUFFIX;
        return getFacetResultByFacetName(bucketBasedFacetName, clazz);
    }

    public <T> Map<Object, T> getFacetResultByFacetName(String facetName, Class<T> clazz) {
        NestableJsonFacet jsonFacetingResponse = response.getJsonFacetingResponse();
        BucketBasedJsonFacet statFacet = jsonFacetingResponse.getBucketBasedFacets(facetName);
        List<BucketJsonFacet> buckets = statFacet.getBuckets();
        Map<Object, T> result = new LinkedHashMap<>();
        for (BucketJsonFacet bucket : buckets) {
            Object key = bucket.getVal();
            T t = getObjectFromBucket(bucket, clazz);
            result.put(key, t);
        }
        return result;
    }

    private <T> T getObjectFromBucket(BucketJsonFacet bucket, Class<T> clazz) {

        if (conversionService.canConvert(BucketJsonFacet.class, clazz)) {
            return conversionService.convert(bucket, clazz);
        }

        T instance = BeanUtils.instantiateClass(clazz);
        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(clazz);
        Set<String> names = bucket.getStatFacetNames();

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if (StringUtils.equals(propertyDescriptor.getName(), "val")) {
                Object val = bucket.getVal();
                setValue(instance, val, propertyDescriptor);
                continue;
            }
            if (StringUtils.equals(propertyDescriptor.getName(), "count")) {
                setValue(instance, bucket.getCount(), propertyDescriptor);
                continue;
            }
            for (String name : names) {
                if (StringUtils.equals(propertyDescriptor.getName(), name)) {
                    setValue(instance, bucket.getStatFacetValue(name), propertyDescriptor);
                    break;
                }
            }
        }
        return instance;
    }

    private <T> void setValue(T instance, Object val, PropertyDescriptor propertyDescriptor) {
        if (val == null) {
            ReflectionUtils.invokeMethod(propertyDescriptor.getWriteMethod(), instance, (Object) null);
            return;
        }
        if (conversionService.canConvert(val.getClass(), propertyDescriptor.getPropertyType())) {
            ReflectionUtils.invokeMethod(propertyDescriptor.getWriteMethod(), instance,
                    conversionService.convert(val, propertyDescriptor.getPropertyType()));
        }else {
            throw new RuntimeException(String.format("类型不能转换: %s -> %s", val.getClass().getName(), propertyDescriptor.getPropertyType()));
        }
    }

    /** --------------------- facet --------------------- */

    /**
     * --------------------- query ---------------------
     */

    public <T> List<T> getQueryResult(Class<T> clazz) {
        SolrDocumentList results = response.getResults();
        if (CollectionUtils.isEmpty(results)){
            return Collections.emptyList();
        }
        List<T> queryResult = new ArrayList<>(results.size());
        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(clazz);
        for (SolrDocument solrDocument : results) {
            if (conversionService.canConvert(SolrDocument.class, clazz)) {
                queryResult.add(conversionService.convert(solrDocument, clazz));
            }else {
                queryResult.add(createObjectFromSolrDocument(clazz, propertyDescriptors, solrDocument));
            }
        }
        return queryResult;
    }

    private <T> T createObjectFromSolrDocument(Class<T> clazz, PropertyDescriptor[] propertyDescriptors, SolrDocument solrDocument) {
        T instance = BeanUtils.instantiateClass(clazz);
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            Collection<String> fieldNames = solrDocument.getFieldNames();
            for (String fieldName : fieldNames) {
                if (StringUtils.equals(propertyDescriptor.getName(),fieldName)) {
                    setValue(instance, solrDocument.getFieldValue(fieldName), propertyDescriptor);
                    break;
                }
            }
        }
        return instance;
    }

    /**
     * --------------------- query ---------------------
     */

    public void addConverter(Converter<?, ?> converter) {
        conversionService.addConverter(converter);
    }

    public void setResponse(QueryResponse response) {
        this.response = response;
    }
}
