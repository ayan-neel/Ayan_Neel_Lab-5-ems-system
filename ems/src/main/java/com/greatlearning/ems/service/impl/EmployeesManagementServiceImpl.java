package com.greatlearning.ems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.ems.model.Employee;
import com.greatlearning.ems.repository.EmployeeRepo;
import com.greatlearning.ems.service.EmployeesManagementService;

@Service
public class EmployeesManagementServiceImpl implements EmployeesManagementService {
	
	@Autowired
	EmployeeRepo crudRepo;
	

	@Override
	public List<Employee> listAllEmployees() {
		return crudRepo.findAll();
	}

	@Override
	public Employee saveorUpdateEmployee(Employee emp) {
		return crudRepo.save(emp);
	}

	@Override
	public void deleteEmployeeById(long id) {
		crudRepo.deleteById(id);
	}

	@Override
	public Employee getEmployeebyId(long id) {
		return crudRepo.findById(id).get();
	}
	
	

}
