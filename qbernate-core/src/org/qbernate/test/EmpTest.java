package org.qbernate.test;

import org.junit.Test;
import org.qbernate.entity.Emp;
import org.qbernate.jdbc.Qession;

/**
 * 类描述：测试Qession的以一些方法
 * 全限定性类名: org.qbernate.test.EmpTest
 * @author 曲健磊
 * @date 2018年8月23日下午2:17:31
 * @version V1.0
 */
public class EmpTest {

	/**
	 * 测试添加方法
	 */
	@Test
	public void testSave() {
		Emp emp = new Emp();
		emp.setEmpno(1);
		emp.setEname("qujianlei");
		emp.setSal(1111.0);
		
		Qession qession = new Qession();
		String insertSql = qession.save(emp);
		System.out.println(insertSql);
	}
	
	/**
	 * 测试删除方法
	 */
	@Test
	public void testDelete() {
		Emp emp = new Emp();
		emp.setEmpno(2);
		
		Qession qession = new Qession();
		String deleteSql = qession.delete(emp);
		System.out.println(deleteSql);
	}
	
	/**
	 * 测试修改方法
	 */
	@Test
	public void testUpdate() {
		Emp emp = new Emp();
		emp.setEmpno(1);
		emp.setEname("qujianlei");
		emp.setSal(3333.0);
		
		Qession qession = new Qession();
		String updateSql = qession.update(emp);
		System.out.println(updateSql);
	}
	
}
