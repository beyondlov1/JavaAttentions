package com.beyond.demo;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.Properties;

@Intercepts(
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        )
)
public class PageInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        BoundSql boundSql = mappedStatement.getBoundSql(args[1]);
        String sql = boundSql.getSql();
        RowBounds rowBounds = (RowBounds) args[2];
        String limit = String.format(" LIMIT %s %s", rowBounds.getOffset(), rowBounds.getLimit());
        sql = sql+limit;
        Class<? extends BoundSql> boundSqlClass = boundSql.getClass();
        Field sqlField = boundSqlClass.getDeclaredField("sql");
        sqlField.setAccessible(true);
        sqlField.set(boundSql,sql);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
