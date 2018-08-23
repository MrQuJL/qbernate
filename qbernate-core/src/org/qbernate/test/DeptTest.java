package org.qbernate.test;

import org.junit.Test;
import org.qbernate.entity.Dept;
import org.qbernate.jdbc.Qession;

/**
 * 类描述：测试Qession的以一些方法
 * 全限定性类名: org.qbernate.test.DeptTest
 * @author 曲健磊
 * @date 2018年8月23日下午4:53:15
 * @version V1.0
 */
public class DeptTest {

	/**
	 * 测试添加方法
	 */
	@Test
	public void testSave() {
		Dept dept = new Dept();
		dept.setDeptId(77);
		dept.setDeptName("研发部");
		dept.setDeptLoc("New York");
		dept.setRemarks("这是备注");
		
		Qession qession = new Qession();
		String insertSql = qession.save(dept);
		System.out.println(insertSql);
	}
	
	/**
	 * 测试删除方法
	 */
	@Test
	public void testDelete() {
		Dept dept = new Dept();
		dept.setDeptId(77);
		
		Qession qession = new Qession();
		String deleteSql = qession.delete(dept);
		System.out.println(deleteSql);
	}
	
	/**
	 * 测试修改方法
	 */
	@Test
	public void testUpdate() {
		Dept dept = new Dept();
		dept.setDeptId(77);
		dept.setDeptName("研发部");
		dept.setDeptLoc("New York");
		dept.setRemarks("这是备注");
		
		Qession qession = new Qession();
		String updateSql = qession.update(dept);
		System.out.println(updateSql);
	}
	
}
