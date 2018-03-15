package org.hbhk.aili.common.share.entity;

import org.hbhk.aili.base.share.entity.BizBaseEntity;
import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Id;
import org.hbhk.aili.mybatis.server.annotation.Table;
/**
 * 员工对应实体
 * @author zb134373
 *
 */
@Table(value="tm_billing_employee")
public class EmpEntity extends BizBaseEntity {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 2649645465711052297L;

	@Id
	@Column("EMP_ID")
	private Long empId;
	//工号
	@Column("EMP_CODE")
	private String empCode;
	
	//姓名
	@Column("EMP_NAME")
	private String empName;
	
	//员工电话
	@Column("EMP_MOBILE")
	private String empMobile;
	//所在部门
	@Column("DEPT_CODE")
	private String deptCode;
	
	//公司代码
	
	@Column("COMP_CODE")
	private String compCode;

	/**
	 * @return the empId
	 */
	public Long getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	/**
	 * @return the empCode
	 */
	public String getEmpCode() {
		return empCode;
	}

	/**
	 * @param empCode the empCode to set
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the empMobile
	 */
	public String getEmpMobile() {
		return empMobile;
	}

	/**
	 * @param empMobile the empMobile to set
	 */
	public void setEmpMobile(String empMobile) {
		this.empMobile = empMobile;
	}

	/**
	 * @return the compCode
	 */
	public String getCompCode() {
		return compCode;
	}

	/**
	 * @param compCode the compCode to set
	 */
	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}

	/**
	 * @return the deptCode
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * @param deptCode the deptCode to set
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	

}	
