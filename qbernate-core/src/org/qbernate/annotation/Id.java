package org.qbernate.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类描述：标记当前属性在数据库中对应的字段为数据库表的主键
 * 全限定性类名: org.qbernate.annotations.Id
 * @author 曲健磊
 * @date 2018年8月23日下午2:31:05
 * @version V1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Id {

}
