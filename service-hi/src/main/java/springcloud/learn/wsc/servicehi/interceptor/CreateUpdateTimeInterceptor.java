package springcloud.learn.wsc.servicehi.interceptor;


import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

/**
 * @author xupeng13
 * @title: CreateUpdateTimeInterceptor
 * @description: TODO
 * @date 2019/5/2416:55
 */
@Component
@Intercepts({
        @Signature(type = org.apache.ibatis.executor.Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class CreateUpdateTimeInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        // 获取 SQL 命令
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        // 获取参数
        Object parameter = invocation.getArgs()[1];

        // 获取私有成员变量
        Field[] declaredFields = parameter.getClass().getDeclaredFields();

        for (Field field : declaredFields) {
            if (field.getAnnotation(CreatedDate.class) != null) {
                if (SqlCommandType.INSERT.equals(sqlCommandType)) { // insert 语句插入 createTime
                    field.setAccessible(true);
                    field.set(parameter, new Date());
                }
            }

            if (field.getAnnotation(LastModifiedDate.class) != null) { // insert 或 update 语句插入 updateTime
                if (SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)) {
                    field.setAccessible(true);
                    field.set(parameter, new Date());
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties0) {
    }
}
