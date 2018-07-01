package com.beyond.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.beyond.dao.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T> {

    private SqlSession sqlSesssion;
    private Class c;

    public BaseDaoImpl() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        c = (Class) actualTypeArguments[0];
    }

    @Override
    public void add(T t) {
        sqlSesssion.insert("com.beyond.mapper." + c.getSimpleName() + "Mapper.add", t);
    }

    @Override
    public void delete(T t) {

    }

    @Override
    public void update(T t) {
        // TODO Auto-generated method stub

    }

    @Override
    public T select() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<T> selectAll() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setSqlSesssion(SqlSession sqlSesssion) {
        this.sqlSesssion = sqlSesssion;
    }

}
