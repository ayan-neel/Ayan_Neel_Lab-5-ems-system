package com.greatlearning.ems.service;

import java.util.List;

import com.greatlearning.ems.model.Employee;

public interface EmployeesManagementService {

	public List<Employee> listAllEmployees();
	public Employee saveorUpdateEmployee(Employee emp);
	public Employee getEmployeebyId(long id);
	public void deleteEmployeeById(long id);
}
