package com.beyond.utils.breakCycle;

import com.beyond.entity.Author;
import com.beyond.entity.Book;
import com.beyond.entity.Order;
import com.beyond.entity.User;
import com.beyond.utils.BreakCycleUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class BreakCycleFetchLoadingStrategy2 implements BreakCycleUtils{

    public Object load(Object obj, int depth) {
        Object object = null;
        try {
            object = copy(obj, depth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    private Object copy(Object source, Integer depth) throws IllegalAccessException, InstantiationException, InvocationTargetException {

        if (depth <= 0) {
            return null;
        }
        if (source == null) {
            return null;
        }

        if(source instanceof Collection){
            Collection oldCollection = (Collection) source;
            Collection collection = oldCollection.getClass().newInstance();
            for (Object object : oldCollection) {
                Object getObject  = copy(object, depth - 1);
                collection.add(getObject);
            }
            return collection;
        }
        Object target = source.getClass().newInstance();
        Method[] methods = source.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("get") && !method.getName().equals("getClass")) {
                Object oldGetObject = method.invoke(source);
                if (oldGetObject != null) {
                    //判断是那种类型
                    if (oldGetObject instanceof Collection) {
                        Collection oldCollection = (Collection) oldGetObject;
                        Collection collection = oldCollection.getClass().newInstance();
                        for (Object object : oldCollection) {
                            Object getObject  = copy(object, depth - 1);
                            collection.add(getObject);
                        }

                        for (Method method1 : methods) {
                            if (method1.getName().equals("set" + method.getName().substring(3))) {
                                method1.invoke(target, collection);
                                break;
                            }
                        }
                    }else if (isMyClass(oldGetObject.getClass())) {
                        Object getObject  = copy(oldGetObject, depth - 1);
                        for (Method method1 : methods) {
                            if (method1.getName().equals("set" + method.getName().substring(3))) {
                                method1.invoke(target, getObject);
                                break;
                            }
                        }
                    }else{
                        for (Method method1 : methods) {
                            if (method1.getName().equals("set" + method.getName().substring(3))) {
                                method1.invoke(target, oldGetObject);
                                break;
                            }
                        }
                    }

                }

            }
        }
        return target;
    }

    private boolean isListContainsMap(List<Map<Integer, Object>> list, Map<Integer, Object> map) {
        for (Map<Integer, Object> map1 : list) {
            for (Integer key1 : map1.keySet()) {
                for (Integer key : map.keySet()) {
                    if (!key.equals(key1)) {
                        if (map1.get(key1).equals(map.get(key))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private Map<Integer, Object> putInMap(Integer depth, Object obj) {
        Map<Integer, Object> map = new HashMap<>();
        map.put(depth, obj);
        return map;
    }

    private boolean isMyClass(Class clazz) {

        if (User.class.isAssignableFrom(clazz)) {
            return true;
        }
        if (Book.class.isAssignableFrom(clazz)) {
            return true;
        }
        if (Author.class.isAssignableFrom(clazz)) {
            return true;
        }
        if (Order.class.isAssignableFrom(clazz)) {
            return true;
        } else {
            return false;
        }

    }

    private void setNull(Object obj, Method method) throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        String substring = method.getName().substring(3);
        String setMethodName = "set" + substring;

        Method[] methods = obj.getClass().getMethods();
        for (Method m : methods) {
            if (m.getName().equals(setMethodName)) {
                m.invoke(obj, new Object[]{null});
            }
        }
    }
}
