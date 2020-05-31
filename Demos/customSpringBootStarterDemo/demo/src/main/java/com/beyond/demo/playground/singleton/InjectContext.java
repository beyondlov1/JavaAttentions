package com.beyond.demo.playground.singleton;

import java.lang.reflect.Constructor;

/**
 * @author: beyond
 * @date: 2019/7/29
 */

public interface InjectContext {
    <T> void registerSingletonBean(Class<T> tClass, T t);

    void registerImplementMapping(Class interfaceClass, Class implementClass);

    Object getSingletonBean(Class tClass);

    Constructor chooseConstructor(Class tClass);

    Class getImplementClass(Class interfaceClass);
}
