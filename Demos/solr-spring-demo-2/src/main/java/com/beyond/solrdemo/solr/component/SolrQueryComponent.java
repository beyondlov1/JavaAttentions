package com.beyond.solrdemo.solr.component;

import org.apache.solr.client.solrj.SolrQuery;

public interface SolrQueryComponent {
    SolrQuery chain(SolrQuery query);
}
