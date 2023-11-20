package com.valtech.training.employeedepartmentspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.valtech.training.employeedepartmentspringboot.entities.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer>{

	@Query(value = "SELECT MIN(deptId) FROM Department")
	int findMinId();
	
	@Query(value = "SELECT MAX(deptId) FROM Department")
	int findMaxId();
	
	@Query(value = "SELECT MIN(deptId) FROM Department WHERE deptId > ?1")
	int findNextId(int deptId);
	
	@Query(value = "SELECT MAX(deptId) FROM Department WHERE deptId < ?1")
	int findPreviousId(int deptId);

}
