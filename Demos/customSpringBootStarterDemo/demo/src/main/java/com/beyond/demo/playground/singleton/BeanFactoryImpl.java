package com.beyond.demo.playground.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class BeanFactoryImpl implements BeanFactory {
    private Map<Class, Object> singletonBeans = new HashMap<>();

    private Map<Class, Class> interfaceImplMap = new HashMap<>();

    public BeanFactoryImpl() {
        
    }

    @Override
    public <T> T getPrototypeBean(Class<T> tClass, Object... params) {
        return tClass.cast(getPrototypeObjectBean(tClass, params));
    }

    private Object getPrototypeObjectBean(Class c, Object... params) {
        return createPrototypeBean(c, params);
    }

    @SuppressWarnings("unchecked")
    private Object createPrototypeBean(Class tClass, Object... params) {
        if (tClass.isInterface()) {
            tClass = interfaceImplMap.get(tClass);
        }
        if (tClass == null) {
            throw new RuntimeException("class not found");
        }

        Class[] paramTypes = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            paramTypes[i] = params[i].getClass();
        }
        try {
            Constructor chosenConstructor = tClass.getConstructor(paramTypes);
            return chosenConstructor.newInstance(params);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> T getBean(Class<T> tClass) {
        return tClass.cast(getObjectBean(tClass));
    }

    private Object getObjectBean(Class tClass) {
        Object o = singletonBeans.get(tClass);
        if (o == null) {
            o = createBean(tClass);
            if (o != null) {
                singletonBeans.put(tClass, o);
            }
        }
        return o;
    }

    private Object createBean(Class tClass) {
        if (tClass.isInterface()) {
            tClass = interfaceImplMap.get(tClass);
        }
        if (tClass == null) {
            throw new RuntimeException("class not found");
        }

        Constructor chosenConstructor = chooseConstructor(tClass);
        int parameterCount = chosenConstructor.getParameterTypes().length;
        Object[] parameters = new Object[chosenConstructor.getParameterTypes().length];
        Class[] parameterTypes = chosenConstructor.getParameterTypes();
        for (int i = 0; i < parameterCount; i++) {
            parameters[i] = getObjectBean(parameterTypes[i]);
        }
        try {
            return chosenConstructor.newInstance(parameters);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Constructor chooseConstructor(Class tClass) {
        return BeanInjectUtils.chooseConstructor(tClass);
    }

}
