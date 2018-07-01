package com.beyond.utils.breakCycle;

import com.beyond.entity.Author;

import com.beyond.entity.Book;
import com.beyond.entity.User;
import com.beyond.utils.BreakCycleUtils;

import com.beyond.entity.Order;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class BreakCycleFetchLoadingStrategy implements BreakCycleUtils {

    private List<Map<Integer, Object>> objects = new ArrayList<>();


    public Object load(Object obj, int depth) {//dept没有用
        System.out.println(obj);
        System.out.println(objects);
        try {
            if (obj == null) {
                return null;
            }

            //如果是Collection, 进入下一层
            if (obj instanceof Collection) {
                Collection collection = (Collection) obj;
                if (collection.size() > 0) {
                    for (Object object : collection) {
                        load(object, depth);
                    }
                }
            } else if (isMyClass(obj.getClass())) {
                if (!isListContainsMap(objects, putInMap(depth, obj))) {
                    objects.add(putInMap(depth, obj));
                }

                //如果是MyClass, 提取方法, 如果有get方法, 就获取get方法, 判断get方法返回值是否为可能引起死循环的类型
                Method[] methods = obj.getClass().getMethods();
                for (Method method : methods) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    Class<?> returnType = method.getReturnType();

                    if (Collection.class.isAssignableFrom(returnType) && method.getName().startsWith("get")) {
                        Collection collection = (Collection)method.invoke(obj);
                        for (Object object : collection) {
                            if(isListContainsMap(objects, putInMap(depth, object))){
                                setNull(obj, method);
                            }else {
                                load(object, depth);
                            }
                        }
                    }

                    if (isMyClass(returnType) && method.getName().startsWith("get")) {
                        Object object = method.invoke(obj);
                        if (isListContainsMap(objects, putInMap(depth, object))) {
                            setNull(obj, method);
                        } else {
                            load(object, depth - 1);
                        }
                    }
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isListContainsMap(List<Map<Integer, Object>> list, Map<Integer, Object> map) {
        for (Map<Integer, Object> map1 : list) {
            for (Integer key1 : map1.keySet()) {
                for(Integer key : map.keySet()){
                    if(!key.equals(key1)){
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
