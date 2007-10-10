/**
 * 
 */
package org.ranjith.swing;

import java.util.Date;

/**
 * @author ranjith
* P U R E L Y  F O R  T E S T I N G
 */
public class Employee {
	private String empNo;
	private Date joinDate;
	private String name;
	private float salary;

	public Employee(String empNo, Date joinDate, String name, float salary) {
		super();
		this.empNo = empNo;
		this.joinDate = joinDate;
		this.name = name;
		this.salary = salary;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}
}
