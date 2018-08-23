package org.qbernate.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.qbernate.annotation.Column;
import org.qbernate.annotation.Id;
import org.qbernate.annotation.Table;

/**
 * 类描述：模拟实现与数据库的会话
 * 全限定性类名: org.qbernate.jdbc.Qession
 * @author 曲健磊
 * @date 2018年8月23日下午2:19:31
 * @version V1.0
 */
public class Qession {

	/**
	 * 向数据库中添加数据
	 * @param obj 待添加的数据
	 * @return 拼接好的sql
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String save(Object obj) {
		StringBuilder builder = new StringBuilder();
		
		// 1.找到表名
		Class clazz = obj.getClass();
		String tabName = findTabName(obj);
		
		// 2.找到字段名
		Field[] fields = clazz.getDeclaredFields();
		StringBuilder colStr = new StringBuilder();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].isAnnotationPresent(Column.class)) {
				Column column = fields[i].getAnnotation(Column.class);
				colStr.append(column.name()).append(", ");
			}
		}
		colStr = new StringBuilder(colStr.subSequence(0, colStr.length() - 2));
		
		// 3.获取每一个属性的值
		StringBuilder valStr = new StringBuilder();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].isAnnotationPresent(Column.class)) {
				StringBuilder temp = new StringBuilder();
				String name = fields[i].getName();
				temp.append("get").append(name.substring(0, 1).toUpperCase()).append(name.substring(1));
				try {
					Method method = clazz.getDeclaredMethod(temp.toString());
					Object value = method.invoke(obj);
					Class reType = method.getReturnType();
					if (String.class == reType) {
						valStr.append("'").append(value).append("'");
					} else {
						valStr.append(value);
					}
					valStr.append(", ");
				} catch (NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		valStr = new StringBuilder(valStr.subSequence(0, valStr.length() - 2));
		
		// 4.根据已知条件拼接一个完整的insert语句
		builder.append("insert into ").append(tabName).append("(").append(colStr)
			.append(")").append(" values(").append(valStr).append(")");
		
		return builder.toString();
	}
	
	/**
	 * 向数据库中删除数据
	 * @param obj 待删除的数据
	 * @return 拼接好的sql
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String delete(Object obj) {
		Class clazz = obj.getClass();
		StringBuilder builder = new StringBuilder();
		
		// 1.找到表名
		String tabName = findTabName(obj);
		builder.append("delete from ").append(tabName);
		
		// 2.找到主键名
		String pkName = findTabPk(obj);
		builder.append(" where ").append(pkName).append(" = ");
		
		// 3.找到主键值
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].isAnnotationPresent(Id.class)) {
				StringBuilder methodName = new StringBuilder(); 
				methodName.append("get").append(fields[i].getName().substring(0, 1).toUpperCase())
					.append(fields[i].getName().substring(1));
				try {
					Method method = clazz.getDeclaredMethod(methodName.toString());
					Object idVal = method.invoke(obj);
					Class reType = method.getReturnType();
					if (String.class == reType) {
						builder.append("'").append(idVal).append("'");
					} else {
						builder.append(idVal);
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
		
		return builder.toString();
	}
	
	/**
	 * 向数据库更新数据
	 * @param obj 待更新的数据
	 * @return 拼接好的sql
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String update(Object obj) {
		Class clazz = obj.getClass();
		StringBuilder builder = new StringBuilder();
		
		// 1.找到表名
		String tabName = findTabName(obj);
		builder.append("update ").append(tabName).append(" set ");
		
		// 2.找到主键名以及主键值
		StringBuilder pkBuilder = new StringBuilder(findTabPk(obj));
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].isAnnotationPresent(Id.class)) {
				StringBuilder methodBuilder = new StringBuilder();
				String name = fields[i].getName();
				methodBuilder.append("get").append(name.substring(0, 1).toUpperCase())
					.append(name.substring(1));
				try {
					Method method = clazz.getDeclaredMethod(methodBuilder.toString());
					Object methodValue = method.invoke(obj);
					Class reType = method.getReturnType();
					if (String.class == reType) {
						pkBuilder.append(" = '").append(methodValue).append("'");
					} else {
						pkBuilder.append(" = ").append(methodValue);
					}
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		
		// 3.找到要修改的字段以及值
		StringBuilder upBuilder = new StringBuilder();
		for (int i = 0; i < fields.length; i++) {
			if (!fields[i].isAnnotationPresent(Id.class) && fields[i].isAnnotationPresent(Column.class)) {
				Column column = fields[i].getAnnotation(Column.class);
				String name = column.name(); // 数据库表字段名
				
				StringBuilder methodBuilder = new StringBuilder();
				String fieldName = fields[i].getName();
				methodBuilder.append("get").append(fieldName.substring(0, 1).toUpperCase())
					.append(fieldName.substring(1));
				try {
					Method method = clazz.getDeclaredMethod(methodBuilder.toString());
					Object mv = method.invoke(obj);
					Class reType = method.getReturnType();
					if (String.class == reType) {
						upBuilder.append(name).append(" = '").append(mv).append("', ");
					} else {
						upBuilder.append(name).append(" = ").append(mv).append(", ");
					}
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		upBuilder = new StringBuilder(upBuilder.toString().substring(0, upBuilder.toString().length() - 2));
		builder.append(upBuilder.toString()).append(" where ").append(pkBuilder.toString());
		
		return builder.toString();
	}
	
	/**
	 * 找到该类所对应的数据库的表名
	 * @param obj 对象名
	 * @return 在数据库中对应的表名
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String findTabName(Object obj) {
		Class clazz = obj.getClass();
		Table table = (Table) clazz.getAnnotation(Table.class);
		String tabName = table.name();
		return tabName;
	}
	
	/**
	 * 获取该类型对象的主键名
	 * @param obj 对象
	 * @return 主键所对应的字段名
	 */
	@SuppressWarnings("rawtypes")
	private String findTabPk(Object obj) {
		String pkName = null;
		Class clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].isAnnotationPresent(Id.class)) {
				Column column = fields[i].getAnnotation(Column.class);
				pkName = column.name();
				break;
			}
		}
		return pkName;
	}
	
}
