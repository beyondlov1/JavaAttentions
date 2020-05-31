package com.beyond.beandefinitionregisterdemos;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;


public class ScanCustomRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        scanner.setIncludeAnnotationConfig(true);
        scanner.addIncludeFilter(new AnnotationTypeFilter(CustomAnnotation2.class));
        scanner.scan("com.beyond.beandefinitionregisterdemos");
    }
}
