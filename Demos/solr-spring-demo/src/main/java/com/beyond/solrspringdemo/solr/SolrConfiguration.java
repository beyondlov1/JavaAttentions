package com.beyond.solrspringdemo.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;

/**
 * @author beyondlov1
 * @date 2019/10/24
 */
@Configuration
public class SolrConfiguration {

    @Value("${spring.data.solr.host}")
    String solrHost;

    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder()
                .withConnectionTimeout(1000)
                .withSocketTimeout(1000)
                .withBaseSolrUrl(solrHost)
                .build();
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient solrClient){
        return new SolrTemplate(solrClient);
    }
}
