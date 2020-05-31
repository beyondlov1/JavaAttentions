package com.beyond.beandefinitionregisterdemos;

import com.beyond.beandefinitionregisterdemos.controller.RegisteredBean;
import com.beyond.beandefinitionregisterdemos.controller.RegisteredProperties;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CustomRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private Map<String, RegisteredProperties> propertiesMap = new HashMap<>();

    @Override
    public void setEnvironment(Environment environment) {
        propertiesMap = Binder.get(environment).bind("registered", Bindable.mapOf(String.class, RegisteredProperties.class)).get();
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Field[] declaredFields = RegisteredProperties.class.getDeclaredFields();
        for (String s : propertiesMap.keySet()) {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RegisteredProperties.class);
            RegisteredProperties properties = propertiesMap.get(s);
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                try {
                    beanDefinitionBuilder.addPropertyValue(declaredField.getName(),declaredField.get(properties));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            registry.registerBeanDefinition(s,beanDefinitionBuilder.getBeanDefinition());

            BeanDefinitionBuilder beanDefinitionBuilder1 = BeanDefinitionBuilder.genericBeanDefinition(RegisteredBean.class);
            beanDefinitionBuilder1.addPropertyValue("name", properties.getName());
            beanDefinitionBuilder1.addPropertyValue("value", properties.getValue());
            registry.registerBeanDefinition(s+"Bean",beanDefinitionBuilder1.getBeanDefinition());
        }


    }
}
