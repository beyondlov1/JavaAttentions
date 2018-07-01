package com.beyond.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

import com.beyond.entity.Author;
import com.beyond.entity.Book;
import com.beyond.entity.Order;
import com.beyond.entity.User;

/*
 * 用来解决生成json对象时死循环的现象
 * 使用方法: obj代表要生成的对象, depth代表要加载的深度 本案例推荐3,基本可覆盖所有信息
 *
 * 此工具只在打开懒加载的情况下好使 (在没有懒加载的情况下, 相互引用的关系已经形成, 而里面的对象数目也已经固定, 将其中的属性改成null,就只将引用指到了null, 所以有几个对象就最多显示
 * 多少信息, 只会少, 不会多,因此不会有重复的对象信息, 这也是dept设置成奇数和偶数结果不一样的原因)
 */
public interface BreakCycleUtils {
    Object load(Object obj, int depth);
}
