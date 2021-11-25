package com.example;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Properties;

/**
 * @author chenshipeng
 * @date 2021/11/25
 */
@Component
@ConfigurationProperties(prefix = "my")
public class DataSourceProperties {

    private Map<String, Properties> datasource;

    public Map<String, Properties> getDatasource() {
        return datasource;
    }

    public void setDatasource(Map<String, Properties> datasource) {
        this.datasource = datasource;
    }
}
