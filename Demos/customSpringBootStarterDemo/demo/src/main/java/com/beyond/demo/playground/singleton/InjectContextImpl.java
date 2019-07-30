package com.beyond.demo.playground.singleton;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: beyond
 * @date: 2019/7/29
 */

public class InjectContextImpl implements InjectContext {

    private Map<Class, Object> singletonBeans = new HashMap<>();
    private Map<Class, Class> interfaceToImplMap = new HashMap<>();

    @Override
    public Class getImplementClass(Class interfaceClass) {
        if (!interfaceClass.isInterface()){
            return interfaceClass;
        }
        Class result;
        try {
            result = Class.forName(interfaceClass.getName() + "Impl");
        } catch (ClassNotFoundException e) {
            result = interfaceToImplMap.get(interfaceClass);
        }
        if (result == null) {
            throw new RuntimeException("no implement class found");
        }
        return result;
    }

    @Override
    public Constructor chooseConstructor(Class tClass) {
        Constructor chosenConstructor = null;
        int parameterCount = 0;
        Constructor[] constructors = tClass.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            if (parameterCount <= constructor.getParameterTypes().length
                    && !containsBasicTypeOrArray(constructor)) {
                chosenConstructor = constructor;
                parameterCount = constructor.getParameterTypes().length;
            }
        }
        return chosenConstructor;
    }

    private boolean containsBasicTypeOrArray(Constructor constructor) {
        Class[] parameterTypes = constructor.getParameterTypes();
        for (Class parameterType : parameterTypes) {
            if (parameterType.isArray()
                    || parameterType == String.class
                    || parameterType == Integer.class
                    || parameterType == Boolean.class
                    || parameterType == Short.class
                    || parameterType == Character.class
                    || parameterType == Long.class
                    || parameterType == Float.class
                    || parameterType == Double.class
                    || parameterType == Byte.class) {
                return true;
            }
        }
        return false;
    }

    @Override
    public <T> void registerSingletonBean(Class<T> tClass, T t) {
        singletonBeans.put(tClass, t);
    }

    @Override
    public Object getSingletonBean(Class tClass) {
        return singletonBeans.get(tClass);
    }

    @Override
    public void registerImplementMapping(Class interfaceClass, Class implementClass) {
        interfaceToImplMap.put(interfaceClass, implementClass);
    }
}
