package com.valtech.training.employeedepartmentspringboot.components;

import org.springframework.stereotype.Component;

@Component
public class HelloEmpDept {

	public String sayHello() {
		return "Hello Department Employees";
	}
}
