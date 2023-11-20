package com.valtech.training.employeedepartmentspringboot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.valtech.training.employeedepartmentspringboot.components.HelloEmpDept;
import com.valtech.training.employeedepartmentspringboot.services.EmployeeDepartmentService;

@SpringBootTest
class EmployeeDepartmentSpringbootApplicationTests {
	
	@Autowired
	private HelloEmpDept helloEmpDept;
	@Autowired
	private EmployeeDepartmentService employeeDepartmentService;

	@Test
	void contextLoads() {
		assertEquals("Hello Department Employees", helloEmpDept.sayHello());
	}
	
	@Test
	void testEmployeeDepartment() {
		assertEquals(2, employeeDepartmentService.getAllDepartments().size());
	}

}
