package com.beyond.solrdemo.solr.result;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.json.BucketBasedJsonFacet;
import org.apache.solr.client.solrj.response.json.BucketJsonFacet;
import org.apache.solr.client.solrj.response.json.NestableJsonFacet;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
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

    public Map<String, Map<?, ?>> getFacetResults(Map<String,Class<?>> classMap) {

        Map<String, Map<?, ?>> results = new HashMap<>();
        NestableJsonFacet jsonFacetingResponse = response.getJsonFacetingResponse();
        Set<String> bucketBasedFacetNames = jsonFacetingResponse.getBucketBasedFacetNames();
        for (String bucketBasedFacetName : bucketBasedFacetNames) {
            results.put(bucketBasedFacetName, getFacetResultByFacetName(bucketBasedFacetName, classMap.get(bucketBasedFacetName)));
        }

        return results;
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
        }
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

    public void addConverter(Converter<?, ?> converter) {
        conversionService.addConverter(converter);
    }

    public void setResponse(QueryResponse response) {
        this.response = response;
    }
}
