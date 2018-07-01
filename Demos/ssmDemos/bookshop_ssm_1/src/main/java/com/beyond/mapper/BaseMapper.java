package com.beyond.mapper;

import java.io.Serializable;
import java.util.List;

public interface BaseMapper<T> {

    void add(T t);

    void delete(T t);

    void update(T t);

    T selectById(Serializable id);

    T selectByExample(T t);

    List<T> selectAll();
}
