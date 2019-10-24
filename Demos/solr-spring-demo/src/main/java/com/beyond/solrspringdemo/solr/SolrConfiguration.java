package com.beyond.solrspringdemo.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.SolrClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author beyondlov1
 * @date 2019/10/24
 */
@Configuration
public class SolrConfiguration {
    @Bean
    public SolrClient solrClient(){
        return new SolrClientBuilder()
    }
}
