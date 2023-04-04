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
public class EmployeeController {
	
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
	@PostMapping("/employees/{id}")
	public String saveEmployee(@ModelAttribute("employee") Employee emp, @PathVariable long id ) {
		
		if(emp==null) {
			emsImpl.deleteEmployeeById(id);
			return "redirect:/employees";
		}
		Employee existingEmp = emsImpl.getEmployeebyId(id);
		existingEmp.setEmail(emp.getEmail());
		existingEmp.setFirstName(emp.getFirstName());
		existingEmp.setLastName(emp.getLastName());
		emsImpl.saveorUpdateEmployee(existingEmp);
		return "redirect:/employees";
		
	}
	@GetMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable long id ) {
		
			emsImpl.deleteEmployeeById(id);
			return "redirect:/employees";
		}
	@GetMapping("/employees/edit/{id}")
	public String editEmployee (@PathVariable long id, Model model) {
		
		Employee existingEmp = emsImpl.getEmployeebyId(id);
		System.out.println(existingEmp);
		model.addAttribute("employee", existingEmp);
		return "edit_employee";
		
	}
	
	

}
