package com.beyond.query.solr.config;

import com.beyond.query.QueryTemplate;
import com.beyond.query.demo.converter.NumberToBigDecimalConverter;
import com.beyond.query.demo.converter.SolrDocumentToBookConverter;
import com.beyond.query.demo.converter.StringToBigDecimalConverter;
import com.beyond.query.solr.BolrTemplate;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.convert.MappingSolrConverter;
import org.springframework.data.solr.core.convert.SolrJConverter;
import org.springframework.data.solr.core.mapping.SimpleSolrMappingContext;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;

@Configuration
public class SolrConfig {
    @Resource
    private Environment environment;

    @Bean
    @ConditionalOnMissingBean
    @Primary
    public SolrClient solrClient(DocumentObjectBinder documentObjectBinder) {
        HttpSolrClient httpSolrClient = new HttpSolrClient.Builder()
                .withBaseSolrUrl(environment.getProperty("spring.data.solr.host"))
                .build();

        return injectBinder(httpSolrClient,documentObjectBinder);
    }

    @Bean
    @ConditionalOnMissingBean
    public DocumentObjectBinder documentObjectBinder(){
        return new DocumentObjectBinder();
    }

    private SolrClient injectBinder(SolrClient solrClient, DocumentObjectBinder binder) {
        if (binder == null){
            return solrClient;
        }
        try {
            Field field = SolrClient.class.getDeclaredField("binder");
            field.setAccessible(true);
            field.set(solrClient,binder);
            return solrClient;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Bean
    public SolrTemplate solrTemplate(SolrClient solrClient) {
        MappingSolrConverter solrConverter = new MappingSolrConverter(new SimpleSolrMappingContext());
        SolrTemplate solrTemplate = new SolrTemplate(solrClient);
        solrConverter.getConversionService().addConverter(new StringToBigDecimalConverter());
        solrConverter.getConversionService().addConverter(new NumberToBigDecimalConverter());
        solrConverter.getConversionService().addConverter(new SolrDocumentToBookConverter());
        solrTemplate.setSolrConverter(solrConverter);
        return solrTemplate;
    }

    @Bean
    public SolrTemplate solrTemplate2(SolrClient solrClient) {
        SolrJConverter solrConverter = new SolrJConverter();
        SolrTemplate solrTemplate = new SolrTemplate(solrClient);
        solrConverter.getConversionService().addConverter(new SolrDocumentToBookConverter());
        solrTemplate.setSolrConverter(solrConverter);
        return solrTemplate;
    }

    private CloudSolrClient getSolrCloudClient() {
        HttpClientBuilder hb = HttpClientBuilder.create();
        hb.setMaxConnPerRoute(150);
        hb.setMaxConnTotal(3500);
        hb.disableAutomaticRetries();
        int timeout = 5;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();
        hb.setDefaultRequestConfig(config);

        HttpClient client = hb.build();
        return new CloudSolrClient.Builder()
                .withZkHost(environment.getRequiredProperty("spring.data.component.zk-host"))
                .withHttpClient(client)
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public BolrTemplate bolrTemplate(SolrClient solrClient, List<Converter> converters){
        BolrTemplate bolrTemplate = new BolrTemplate(solrClient);
        if (converters == null){
            return bolrTemplate;
        }
        for (Converter converter : converters) {
            bolrTemplate.addConverter(converter);
        }
        return bolrTemplate;
    }

    @Bean
    public QueryTemplate<SolrQuery, QueryResponse> solrQueryTemplate(BolrTemplate bolrTemplate){
        bolrTemplate.setCollection("techproducts");
        return bolrTemplate;
    }
}
