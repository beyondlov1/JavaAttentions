package com.beyond.demo.playground.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeanInjectorImpl implements BeanInjector {

    private InjectContext context;

    private BeanFactoryImpl factory;


    public BeanInjectorImpl(InjectContext context) {
        this.context = context;
        this.factory = new BeanFactoryImpl(context);
    }

    @Override
    public void inject(Object o, Object... params) {
        inject(o,new Class[0],params);
    }

    @Override
    public void inject(Object o, Class[] implementClasses, Object... params) {
        List<Object> injectingObjects = new ArrayList<>();
        injectInternal(o, injectingObjects,implementClasses, params);
    }

    private void injectInternal(Object o,List<Object> injectingObjects,Object... params) {
        Field[] declaredFields = getAllFields(o);
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(SingletonInject.class)) {
                injectSingleton(o, factory, field,injectingObjects);
            }

            if (field.isAnnotationPresent(PrototypeInject.class)) {
                injectPrototype(o, factory, field, params,injectingObjects);
            }
        }
    }

    private Field[] getAllFields(Object o){
        List<Field> fieldList = new ArrayList<>() ;
        Class tempClass = o.getClass();
        while (tempClass != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
            fieldList.addAll(Arrays.asList(tempClass .getDeclaredFields()));
            tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
        }
        return fieldList.toArray(new Field[0]);
    }

    @SuppressWarnings("unchecked")
    private void injectSingleton(Object o, BeanFactoryImpl factory,
                                 Field field,
                                 List<Object> injectingObjects) {
        injectingObjects.add(o);
        Class type = getFieldImplType(field);
        Object bean = factory.getBean(type);
        if (!injectingObjects.contains(bean)){
            injectInternal(bean,injectingObjects);
        }
        field.setAccessible(true);
        try {
            field.set(o, bean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Class getFieldImplType(Field field) {
        Class type = field.getType();
        if (field.isAnnotationPresent(Qualifier.class)){
            Qualifier qualifier = field.getAnnotation(Qualifier.class);
            type = qualifier.implementClass();
        }
        return type;
    }

    private void injectPrototype(Object o, BeanFactoryImpl factory, Field field, Object[] params, List<Object> injectingObjects) {
        PrototypeInject prototypeInject = field.getAnnotation(PrototypeInject.class);
        boolean consume = prototypeInject.paramConsume();
        Class<?> type =getFieldImplType(field);
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
        Object bean = factory.getPrototypeBean(type, thisParams);
        injectInternal(bean,injectingObjects);
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
        type = context.getImplementClass(type);
        return context.chooseConstructor(type);
    }
}
