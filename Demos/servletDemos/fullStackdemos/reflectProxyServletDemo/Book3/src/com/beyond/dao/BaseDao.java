package com.beyond.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao {
	<T> int addBean(T t);

	<T> int deleteBean(Class<T> c, String id);

	<T> int deleteBean(Class<T> c, String key, Object value);

	<T> int updateBean(T t);

	<T> int updateBean(String id, T t);

	<T> int updateBean(String key, Object value, T t);

	<T> int updateBean(Class<T> c, String id, Map<String, Object> map); // 只修改某些属性

	<T> int updateBean(Class<T> c, String whereKey, String whereValue, Map<String, Object> map); // 只修改某些属性

	<T> T selectBean(Class<T> c, String id);

	<T> T selectBean(Class<T> c, String key, Object value);

	<T> List<T> selectAllBean(Class<T> c);

	// 分页查找
	<T> List<T> selectAllBean(Class<T> c, int startIndex, int offset);

	<T> List<T> selectAllBean(Class<T> c, int startIndex, int offset, String orderColumn);

	<T> List<T> selectAllBean(Class<T> c, int startIndex, int offset, String orderColumn, String orderMethod);

	<T> int selectTotalCount(Class<T> c);// 获得总数

	<T> int selectCountByCondition(Class<T> c, String condition);

}
