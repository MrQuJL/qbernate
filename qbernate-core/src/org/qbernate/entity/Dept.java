package org.qbernate.entity;

import org.qbernate.annotation.Column;
import org.qbernate.annotation.Id;
import org.qbernate.annotation.Table;

@Table(name="dept")
public class Dept {

	@Id	
	@Column(name="dept_id")
	private Integer deptId;
	
	@Column(name="dept_name")
	private String deptName;
	
	@Column(name="dept_loc")
	private String deptLoc;
	
	@Column(name="remarks")
	private String remarks;

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptLoc() {
		return deptLoc;
	}

	public void setDeptLoc(String deptLoc) {
		this.deptLoc = deptLoc;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
