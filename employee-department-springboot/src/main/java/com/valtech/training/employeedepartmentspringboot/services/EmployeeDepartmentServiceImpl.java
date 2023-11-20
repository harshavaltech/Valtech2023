package com.valtech.training.employeedepartmentspringboot.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.valtech.training.employeedepartmentspringboot.entities.Department;
import com.valtech.training.employeedepartmentspringboot.entities.Employee;
import com.valtech.training.employeedepartmentspringboot.repositories.DepartmentRepo;
import com.valtech.training.employeedepartmentspringboot.repositories.EmployeeRepo;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EmployeeDepartmentServiceImpl implements EmployeeDepartmentService {

	@Autowired
	private EmployeeRepo employeeRepo;
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@PostConstruct
	public void populateDepartmentAndEmployee() {
		Department dept = new Department("FrontEnd Developers", "Bengaluru");
		Department dept1 = new Department("BackEnd Developers", "Bengaluru");
		departmentRepo.save(dept);
		departmentRepo.save(dept1);
		Employee emp = new Employee("I Suhas", 22, 0, 0, 40000, dept);
		Employee emp1 = new Employee("Laxman", 22, 0, 0, 35000, dept1);
		employeeRepo.save(emp);
		employeeRepo.save(emp1);
		departmentRepo.save(new Department("Mobility", "Pune"));
	}
	
	//Create, Update, Get , getAll Department
	@Override
	public Department createDepartment(Department department) {
		System.out.println(departmentRepo.getClass().getName());
		return departmentRepo.save(department);
	}
	
	@Override
	public Department updateDepartment(Department department) {
		return departmentRepo.save(department);
	}
	
	@Override
	public Department getDepartment(int deptId) {
		return departmentRepo.getReferenceById(deptId);
	}
	
	@Override
	public List<Department> getAllDepartments() {
		return departmentRepo.findAll();
	}
	
	//Create, Update, Get , getAll Employee
	@Override
	public 	Employee createEmployee(Employee employee) {
		System.out.println(employeeRepo.getClass().getName());
		return employeeRepo.save(employee);
	}
	
	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	@Override
	public Employee getEmployee(int id) {
		return employeeRepo.getReferenceById(id);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public int getFirstId() {
		return departmentRepo.findMinId();
	}

	@Override
	public int getLastId() {
		return departmentRepo.findMaxId();
	}

	@Override
	public int getPreviousId(int deptId) {
		int id = departmentRepo.findPreviousId(deptId);
		return id == 0 ? getLastId() : id;
	}

	@Override
	public int getNextId(int deptId) {
		int id = departmentRepo.findNextId(deptId);
		return (id == 0) ? getFirstId() : id;
	}

	@Override
	public List<Employee> getAllEmployeesByDeptId(int deptId) {
		return employeeRepo.findEmployeeByDeptId(deptId);
	}
	
}
