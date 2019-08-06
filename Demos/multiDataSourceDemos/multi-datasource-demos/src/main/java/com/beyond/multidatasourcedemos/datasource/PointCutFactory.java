package com.beyond.multidatasourcedemos.datasource;

import org.aspectj.lang.annotation.Pointcut;

public class PointCutFactory {

    @Pointcut("@annotation(com.beyond.multidatasourcedemos.datasource.DataSource)")
    public void dataSourcePointCut(){}

}

