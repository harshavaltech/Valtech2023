package com.valtech.training.employeedepartmentspringboot.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.valtech.training.employeedepartmentspringboot.models.DepartmentModel;
import com.valtech.training.employeedepartmentspringboot.models.EmployeeModel;
import com.valtech.training.employeedepartmentspringboot.services.EmployeeDepartmentService;

@Controller
public class EmployeeDepartmentController {

	@Autowired
	private EmployeeDepartmentService employeeDepartmentService;


//	@Autowired
//	private JdbcTemplate jdbcTemplate; //using jdbc template foro querying list

	@GetMapping("/departmentslist")
	public String getAllDepartments(Model model) {
		model.addAttribute("departments", employeeDepartmentService.getAllDepartments().stream()
				.map(department -> new DepartmentModel(department)).collect(Collectors.toList()));
		return "departmentslist";
	}

	@GetMapping("/newDepartment")
	public String newDepartment(Model model) {
		model.addAttribute("department", new DepartmentModel());
		return "addOrEditDepartment";
	}

	@PostMapping(path = "/saveDept", params = "submit")
	public String saveDepartment(@ModelAttribute DepartmentModel departmentModel, Model model) {
		employeeDepartmentService.createDepartment(departmentModel.getDepartment());
		model.addAttribute("departments", employeeDepartmentService.getAllDepartments().stream()
				.map(department -> new DepartmentModel(department)).collect(Collectors.toList()));
		return "departmentslist";
	}

	@PostMapping(path = "/saveDept", params = "cancel")
	public String cancelDepartment(Model model) {
		model.addAttribute("departments", employeeDepartmentService.getAllDepartments().stream()
				.map(department -> new DepartmentModel(department)).collect(Collectors.toList()));
		return "departmentslist";
	}

	@GetMapping("/editDept")
	public String editDepartment(@RequestParam("deptId") int deptId, Model model) {
		model.addAttribute("department", new DepartmentModel(employeeDepartmentService.getDepartment(deptId)));
		return "addOrEditDepartment";
	}

	@GetMapping("/employeeslist")
//	@ResponseBody
	public String getAllEmployees(Model model) {
		model.addAttribute("employees", employeeDepartmentService.getAllEmployees().stream()
				.map(employee -> new EmployeeModel(employee)).collect(Collectors.toList()));
//		List<Map<String, Object>> employees = jdbcTemplate.queryForList("select * from employee"); //without creating Model
//		model.addAttribute("employees", employees);
		return "employeeslist";
	}

	@GetMapping("/newEmployee")
	public String newEmployee(Model model) {
		model.addAttribute("employee", new EmployeeModel());
		return "addEmployee";
	}

	@PostMapping(path = "/save", params = "submit")
	public String saveEmployee(@ModelAttribute EmployeeModel employeeModel, Model model) {
		employeeDepartmentService.createEmployee(employeeModel.getEmployee());
		model.addAttribute("employees", employeeDepartmentService.getAllEmployees().stream()
				.map(employee -> new EmployeeModel(employee)).collect(Collectors.toList()));
		return "employeeslist";
	}

	@PostMapping(path = "/save", params = "cancel")
	public String cancelEmployee(Model model) {
		model.addAttribute("employees", employeeDepartmentService.getAllEmployees().stream()
				.map(employee -> new EmployeeModel(employee)).collect(Collectors.toList()));
		return "redirect:employeeslist";
	}

	@GetMapping("/edit")
	public String editEmployee(@RequestParam("id") int id, Model model) {
		model.addAttribute("employee", new EmployeeModel(employeeDepartmentService.getEmployee(id)));
		return "editEmployee";
	}

	@GetMapping("/departmentEmployees")
	public String firstDepartmentEmployees(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		model.addAttribute("department",
				new DepartmentModel(employeeDepartmentService.getDepartment(employeeDepartmentService.getFirstId())));
		model.addAttribute("employees",
				employeeDepartmentService.getAllEmployeesByDeptId(employeeDepartmentService.getFirstId()).stream()
						.map(employee -> new EmployeeModel(employee)).collect(Collectors.toList()));
		session.setAttribute("current", employeeDepartmentService.getFirstId());
		return "departmentEmployees";
	}

	@PostMapping(path = "/dept", params = "submit")
	public String listDepartmentEmployees(@RequestParam("submit") String submit, HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		int current = (int) session.getAttribute("current");
		if (submit.equals("First")) {	
			current = employeeDepartmentService.getFirstId();
			model.addAttribute("department",
					new DepartmentModel(employeeDepartmentService.getDepartment(current)));
			model.addAttribute("employees",
					employeeDepartmentService.getAllEmployeesByDeptId(current).stream()
							.map(employee -> new EmployeeModel(employee)).collect(Collectors.toList()));
		} else if (submit.equals("Next")) {
			if (current != employeeDepartmentService.getLastId()) {
			current = employeeDepartmentService.getNextId(current);
			model.addAttribute("department",
					new DepartmentModel(employeeDepartmentService.getDepartment(current)));
			model.addAttribute("employees",
					employeeDepartmentService.getAllEmployeesByDeptId(current)
							.stream().map(employee -> new EmployeeModel(employee)).collect(Collectors.toList()));
			} else {
				current = employeeDepartmentService.getFirstId();
				model.addAttribute("department",
						new DepartmentModel(employeeDepartmentService.getDepartment(current)));
				model.addAttribute("employees",
						employeeDepartmentService.getAllEmployeesByDeptId(current)
								.stream().map(employee -> new EmployeeModel(employee)).collect(Collectors.toList()));
			}
		}else if (submit.equals("Previous")) {
			if(current != employeeDepartmentService.getFirstId()) {
			current = employeeDepartmentService.getPreviousId(current);
			model.addAttribute("department",
					new DepartmentModel(employeeDepartmentService.getDepartment(current)));
			model.addAttribute("employees",
					employeeDepartmentService.getAllEmployeesByDeptId(current)
							.stream().map(employee -> new EmployeeModel(employee)).collect(Collectors.toList()));
			} else {
				current = employeeDepartmentService.getLastId();
				model.addAttribute("department",
						new DepartmentModel(employeeDepartmentService.getDepartment(current)));
				model.addAttribute("employees",
						employeeDepartmentService.getAllEmployeesByDeptId(current)
								.stream().map(employee -> new EmployeeModel(employee)).collect(Collectors.toList()));
			}
		} else if (submit.equals("Last")) {
			current = employeeDepartmentService.getLastId();
			model.addAttribute("department",
					new DepartmentModel(employeeDepartmentService.getDepartment(current)));
			model.addAttribute("employees",
					employeeDepartmentService.getAllEmployeesByDeptId(current)
							.stream().map(employee -> new EmployeeModel(employee)).collect(Collectors.toList()));
		}
		session.setAttribute("current", current);
		return "departmentEmployees";
	}

}
