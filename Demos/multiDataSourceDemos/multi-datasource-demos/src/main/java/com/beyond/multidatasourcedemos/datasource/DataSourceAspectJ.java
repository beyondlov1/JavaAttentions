package com.beyond.multidatasourcedemos.datasource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class DataSourceAspectJ {

    @Before("com.beyond.multidatasourcedemos.datasource.PointCutFactory.dataSourcePointCut() && @annotation(dataSource)")
    public void chooseDataSource(DataSource dataSource){
        DataSourceType type = dataSource.type();
        DataSourceKeyHolder.setDataSourceType(type);
    }

}
