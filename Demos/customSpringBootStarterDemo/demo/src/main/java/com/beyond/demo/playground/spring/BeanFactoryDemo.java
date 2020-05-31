package com.beyond.demo.playground.spring;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class BeanFactoryDemo {
    public static void main(String[] args) {
        propertiesBeanDefinitionReader();
        xmlBeanDefinitionReader();
    }

    private static void xmlBeanDefinitionReader() {
        DefaultListableBeanFactory beanFactory =new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(new ClassPathResource("com/beyond/demo/playground/spring/beans.xml"));
        PropertyPlaceholderConfigurer bean = beanFactory.getBean(PropertyPlaceholderConfigurer.class);
        bean.postProcessBeanFactory(beanFactory);
        Object hello = beanFactory.getBean("hello");
        System.out.println(hello);
    }

    private static void propertiesBeanDefinitionReader() {
        DefaultListableBeanFactory beanFactory =new DefaultListableBeanFactory();
        BeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
        ClassPathResource classPathResource = new ClassPathResource("com/beyond/demo/playground/spring/beans.properties");
        reader.loadBeanDefinitions(classPathResource);
        Person hello = beanFactory.getBean("hello", Person.class);
        System.out.println(hello);
    }
}
