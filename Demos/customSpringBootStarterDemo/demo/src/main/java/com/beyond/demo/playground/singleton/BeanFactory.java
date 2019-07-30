package com.beyond.demo.playground.singleton;

public interface BeanFactory {

    <T> T getBean(Class<T> tClass);

    <T> T getPrototypeBean(Class<T> tClass, Object... params);

}
