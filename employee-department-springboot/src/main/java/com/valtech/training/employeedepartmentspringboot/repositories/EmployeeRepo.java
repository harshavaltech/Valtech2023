package com.valtech.training.employeedepartmentspringboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.valtech.training.employeedepartmentspringboot.entities.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	
	@Query(value = "SELECT e FROM Employee e WHERE dept_id = ?1")
	List<Employee> findEmployeeByDeptId(int deptId);

}
