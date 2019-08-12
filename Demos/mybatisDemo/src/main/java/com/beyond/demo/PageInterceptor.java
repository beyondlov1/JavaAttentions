package com.beyond.demo;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
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
        if (args[2] == RowBounds.DEFAULT){
            return invocation.proceed();
        }
        MappedStatement mappedStatement = (MappedStatement) args[0];
        BoundSql boundSql = mappedStatement.getBoundSql(args[1]);
        String sql = boundSql.getSql();
        RowBounds rowBounds = (RowBounds) args[2];
        String limit = String.format(" LIMIT %s,%s", rowBounds.getOffset(), rowBounds.getLimit());
        if (sql.endsWith(";")){
            sql = sql.substring(0,sql.lastIndexOf(";"));
        }
        sql = sql+limit;
        SqlSource sqlSource = new StaticSqlSource(mappedStatement.getConfiguration(),sql);
        Field sqlSourceField = MappedStatement.class.getDeclaredField("sqlSource");
        sqlSourceField.setAccessible(true);
        sqlSourceField.set(mappedStatement,sqlSource);
        args[2] = RowBounds.DEFAULT;
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
