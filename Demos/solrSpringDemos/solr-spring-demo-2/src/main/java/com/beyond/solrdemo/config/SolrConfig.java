package com.beyond.solrdemo.config;

import com.beyond.solrdemo.entity.NumberToBigDecimalConverter;
import com.beyond.solrdemo.entity.SolrDocumentToBookConverter;
import com.beyond.solrdemo.entity.StringToBigDecimalConverter;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.convert.MappingSolrConverter;
import org.springframework.data.solr.core.convert.SolrJConverter;
import org.springframework.data.solr.core.mapping.SimpleSolrMappingContext;

import javax.annotation.Resource;

@Configuration
public class SolrConfig {
    @Resource
    private Environment environment;

    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder()
                .withBaseSolrUrl(environment.getProperty("spring.data.solr.host"))
                .build();
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
                .withZkHost(environment.getRequiredProperty("spring.data.solr.zk-host"))
                .withHttpClient(client)
                .build();
    }

}
