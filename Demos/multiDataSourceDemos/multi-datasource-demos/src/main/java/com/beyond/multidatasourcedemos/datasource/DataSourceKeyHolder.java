package com.beyond.multidatasourcedemos.datasource;

public class DataSourceKeyHolder {
    private static ThreadLocal<DataSourceType> typeContainer = new ThreadLocal<>();

    public static void setDataSourceType(DataSourceType type){
        typeContainer.set(type);
    }

    public static DataSourceType getDataSourceType(){
        return typeContainer.get();
    }
}
