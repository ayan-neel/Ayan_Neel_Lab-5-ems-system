package com.greatlearning.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.greatlearning.ems.model.Employee;
import com.greatlearning.ems.service.EmployeesManagementService;

@Controller
public class WelcomeController {
	
	@Autowired
	EmployeesManagementService emsImpl;
	
	@GetMapping("/home")
	public String getHomePage() {
		System.out.println("Hi");
		return "home";
		
	}
	@GetMapping("/employees")
	public String showEmployees(Model model) {
		
		List<Employee> employees = emsImpl.listAllEmployees();
		model.addAttribute("employees", employees);
		return "employees";
		
	}
	
	@GetMapping("/employees/new")
	public String newEmployee(Model model) {
		
		model.addAttribute("employee", new Employee());
		return "create_employee";
		
	}
	
	@PostMapping("/employees")
	public String saveEmployee(@ModelAttribute("employee")Employee emp) {
		emsImpl.saveorUpdateEmployee(emp);
		return "redirect:/employees";
		
	}
	@PostMapping("/employees/edit/{id}")
	public String editEmployee (@PathVariable long id, Model model) {
		
		Employee existingEmp = emsImpl.getEmployeebyId(id);
		model.addAttribute("employee", existingEmp);
		return "edit_employee";
		
	}
	
	

}
