package com.valtech.training.employeedepartmentspringboot.models;

import java.util.List;

import com.valtech.training.employeedepartmentspringboot.entities.Department;
import com.valtech.training.employeedepartmentspringboot.entities.Employee;

public class DepartmentModel {

	private int deptId;
	private String deptName;
	private String deptLocation;

	public DepartmentModel() {
	}

	public DepartmentModel(Department department) {
		super();
		this.deptId = department.getDeptId();
		this.deptName = department.getDeptName();
		this.deptLocation = department.getDeptLocation();
	}

	public Department getDepartment() {
		return new Department(deptId, deptName, deptLocation);
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptLocation() {
		return deptLocation;
	}

	public void setDeptLocation(String deptLocation) {
		this.deptLocation = deptLocation;
	}

}
