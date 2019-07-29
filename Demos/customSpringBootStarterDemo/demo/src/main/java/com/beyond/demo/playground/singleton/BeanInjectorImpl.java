package com.beyond.demo.playground.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class BeanInjectorImpl implements BeanInjector {

    private BeanFactoryImpl factory = new BeanFactoryImpl();

    @Override
    public void inject(Object o, Object... params) {
        Class<?> aClass = o.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(SingletonInject.class)) {
                injectSingleton(o, factory, field);
            }

            if (field.isAnnotationPresent(PrototypeInject.class)) {
                injectPrototype(o, factory, field, params);
            }
        }
    }

    private void injectSingleton(Object o, BeanFactoryImpl factory, Field field) {
        Object bean = factory.getBean(field.getType());
        field.setAccessible(true);
        try {
            field.set(o, bean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void injectPrototype(Object o, BeanFactoryImpl factory, Field field, Object[] params) {
        PrototypeInject prototypeInject = field.getAnnotation(PrototypeInject.class);
        boolean consume = prototypeInject.paramConsume();
        Class<?> type = field.getType();
        Constructor constructor = choseConstructor(type);
        Class[] parameterTypes = constructor.getParameterTypes();
        Object[] thisParams = new Object[parameterTypes.length];
        int i = 0;
        for (Class parameterType : parameterTypes) {
            boolean found = false;
            for (int j = 0; j < params.length; j++) {
                Object param = params[j];
                if (parameterType.isInstance(param)) {
                    thisParams[i] = param;
                    if (consume) {
                        params[j] = null;
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                setSingletonParam(thisParams, i, parameterType);
            }
            i++;
        }

        Object bean = factory.getPrototypeBean(field.getType(), thisParams);
        field.setAccessible(true);
        try {
            field.set(o, bean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void setSingletonParam(Object[] thisParams, int i, Class parameterType) {
        thisParams[i] = factory.getBean(parameterType);
    }

    private Constructor choseConstructor(Class<?> type) {
        return BeanInjectUtils.chooseConstructor(type);
    }
}
