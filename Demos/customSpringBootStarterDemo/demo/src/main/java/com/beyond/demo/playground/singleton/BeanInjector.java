package com.beyond.demo.playground.singleton;

public interface BeanInjector {
    void inject(Object o, Object... params);

    void inject(Object o, Class[] implementClass, Object... params);
}
