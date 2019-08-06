package com.beyond.multidatasourcedemos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.beyond.multidatasourcedemos.mapper")
public class MultiDatasourceDemosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiDatasourceDemosApplication.class, args);
    }

}
