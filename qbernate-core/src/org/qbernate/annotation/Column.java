package org.qbernate.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类描述：建立实体类字段与数据库表字段之间的映射关系
 * 全限定性类名: org.qbernate.annotations.Column
 * @author 曲健磊
 * @date 2018年8月23日下午2:28:03
 * @version V1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
	
	/**
	 * 指明当前字段在数据库表中对应的字段名
	 * @return 该字段在数据库中对应的表字段
	 */
	String name() default "";
	
}
