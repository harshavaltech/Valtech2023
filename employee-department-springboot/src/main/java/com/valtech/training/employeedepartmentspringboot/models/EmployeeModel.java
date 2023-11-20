package com.valtech.training.employeedepartmentspringboot.models;

import com.valtech.training.employeedepartmentspringboot.entities.Department;
import com.valtech.training.employeedepartmentspringboot.entities.Employee;

public class EmployeeModel {

	private int id;
	private String name;
	private int age;
	private int experience;
	private int seniority;
	private int salary;
	private Department department;

	public EmployeeModel() {
	}

	public EmployeeModel(Employee employee) {
		super();
		this.id = employee.getId();
		this.name = employee.getName();
		this.age = employee.getAge();
		this.experience = employee.getExperience();
		this.seniority = employee.getSeniority();
		this.salary = employee.getSalary();
		this.department = employee.getDepartment();
	}
	
	public Employee getEmployee() {
		return new Employee(id, name, age, experience, seniority, salary, department);
	}
	
	

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getSeniority() {
		return seniority;
	}

	public void setSeniority(int seniority) {
		this.seniority = seniority;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

}
