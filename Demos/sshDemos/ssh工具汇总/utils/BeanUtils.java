package com.beyond.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

public class BeanUtils {
	public static <T> T fillBean(HttpServletRequest request, Class<T> c) {
		T t = null;
		try {
			t = c.newInstance();
			org.apache.commons.beanutils.BeanUtils.populate(t, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	public static Map<String, Object> getNoNullMapFromBean(Object bean)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		Map<String, Object> newMap = new HashMap<>();
		Map<String, Object> map = org.apache.commons.beanutils.BeanUtils.describe(bean);

		// ªÒ»°field√˚
		List<String> fieldNames = new ArrayList<>();
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			fieldNames.add(field.getName());
		}

		if (map == null || map.size() == 0) {
			return null;
		}

		Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
		if (!it.hasNext()) {
			return null;
		}

		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			if (fieldNames.contains(key)) {
				if (value != null && !"".equals(value.toString())) {
					newMap.put(key, value);
				}
			}

		}
		if (newMap.size() == 0) {
			return null;
		}
		return newMap;
	}
}
