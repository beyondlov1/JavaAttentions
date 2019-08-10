package com.beyond.beandefinitionregisterdemos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({CustomRegistrar.class, ScanCustomRegistrar.class})
public class BeanDefinitionRegisterDemosApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeanDefinitionRegisterDemosApplication.class, args);
    }

}
