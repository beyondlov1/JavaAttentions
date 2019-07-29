package com.beyond.demo.playground.singleton;

import java.lang.reflect.Constructor;

public class BeanInjectUtils {

    private static class BeanInjectorHolder{
        private static final BeanInjector INSTANCE = new BeanInjectorImpl();
    }

    public static BeanInjector getBeanInjector(){
        return BeanInjectorHolder.INSTANCE;
    }

    public static void inject(Object o, Object...params){
        getBeanInjector().inject(o,params);
    }

    public static Constructor chooseConstructor(Class tClass) {
        Constructor chosenConstructor = null;
        int parameterCount = 0;
        Constructor[] constructors = tClass.getConstructors();
        for (Constructor constructor : constructors) {
            if (parameterCount <= constructor.getParameterTypes().length
                    && !containsBasicTypeOrArray(constructor)) {
                chosenConstructor = constructor;
                parameterCount = constructor.getParameterTypes().length;
            }
        }
        return chosenConstructor;
    }

    private static boolean containsBasicTypeOrArray(Constructor constructor) {
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
}
