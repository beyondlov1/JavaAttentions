package com.beyond.demo;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MALE: 1
 * FEMALE: 2
 * @param <E>
 */
public class CustomEnumTypeHanlder<E extends Enum<E>> extends BaseTypeHandler<E> {

    private E[] enumConstants;

    public CustomEnumTypeHanlder(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        } else {
            enumConstants = type.getEnumConstants();
        }

    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E e, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, String.valueOf(e.ordinal() + 1));
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String value = resultSet.getString(s);
        if (value == null) {
            return null;
        }
        Integer order = Integer.valueOf(value);
        return s == null ? null : enumConstants[order - 1];
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String value = resultSet.getString(i);
        if (value == null) {
            return null;
        }
        Integer order = Integer.valueOf(value);
        return enumConstants[order - 1];
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String value = callableStatement.getString(i);
        if (value == null) {
            return null;
        }
        Integer order = Integer.valueOf(value);
        return enumConstants[order - 1];
    }
}
