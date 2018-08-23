package org.qbernate.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类描述：建立实体类与数据库表之间的映射关系
 * 全限定性类名: org.qbernate.annotations.Table
 * @author 曲健磊
 * @date 2018年8月23日下午2:25:02
 * @version V1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {
	
	/**
	 * 指明当前实体类在数据库中对应的表名
	 * @return 在数据库中对应的表名
	 */
	String name() default "";
	
}
