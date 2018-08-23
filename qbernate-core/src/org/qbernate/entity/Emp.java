package org.qbernate.entity;

import org.qbernate.annotation.Column;
import org.qbernate.annotation.Id;
import org.qbernate.annotation.Table;

/**
 * 类描述：雇员实体类
 * 全限定性类名: org.qbernate.entity.Emp
 * @author 曲健磊
 * @date 2018年8月23日下午2:33:05
 * @version V1.0
 */
@Table(name="emp")
public class Emp {

	@Id
	@Column(name="emp_no")
	private Integer empno;
	
	@Column(name="emp_name")
	private String ename;
	
	@Column(name="emp_sal")
	private Double sal;

	public Integer getEmpno() {
		return empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Double getSal() {
		return sal;
	}

	public void setSal(Double sal) {
		this.sal = sal;
	}

}
