package com.beyond.utils.breakCycle;

import com.beyond.entity.Author;
import com.beyond.entity.Book;
import com.beyond.entity.Order;
import com.beyond.entity.User;
import com.beyond.utils.BreakCycleUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;


/*
 * 用来解决生成json对象时死循环的现象
 * 使用方法: obj代表要生成的对象, depth代表要加载的深度 本案例推荐3,基本可覆盖所有信息
 *
 * 此工具只在打开懒加载的情况下好使 (在没有懒加载的情况下, 相互引用的关系已经形成, 而里面的对象数目也已经固定, 将其中的属性改成null,就只将引用指到了null, 所以有几个对象就最多显示
 * 多少信息, 只会少, 不会多,因此不会有重复的对象信息, 这也是dept设置成奇数和偶数结果不一样的原因)
 */
public class BreakCycleLazyLoadingStrategy implements BreakCycleUtils {

    public Object load(Object obj, int depth) {
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
                //如果是MyClass, 提取方法, 如果有get方法, 就获取get方法, 判断get方法返回值是否为可能引起死循环的类型
                Method[] methods = obj.getClass().getMethods();
                for (Method method : methods) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    Class<?> returnType = method.getReturnType();

                    if (Collection.class.isAssignableFrom(returnType) && method.getName().startsWith("get")) {
                        Collection collection = (Collection) method.invoke(obj);

                        if (collection != null && collection.size() > 0) {

                            for (Object object : collection) {
                                if (isMyClass(object.getClass())) {
                                    if (depth > 0) {
                                        load(object, depth - 1);
                                    } else {
                                        setNull(obj, method);
                                    }
                                }
                            }
                        }
                    }

                    if (isMyClass(returnType) && method.getName().startsWith("get")) {
                        Object object = method.invoke(obj);
                        if (object != null) {
                            if (depth > 0) {
                                load(object, depth - 1);
                            } else {
                                setNull(obj, method);
                            }
                        }
                    }
                }
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return obj;
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
