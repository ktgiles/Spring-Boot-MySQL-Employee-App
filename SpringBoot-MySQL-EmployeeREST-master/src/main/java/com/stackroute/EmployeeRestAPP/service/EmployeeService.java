package com.stackroute.EmployeeRestAPP.service;

import java.util.List;

import com.stackroute.EmployeeRestAPP.exceptions.EmployeeWithTheIDAlreadyPresentException;
import com.stackroute.EmployeeRestAPP.exceptions.EmployeeWithTheIDNotPresentException;
import com.stackroute.EmployeeRestAPP.model.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployee();
	Employee getEmployeeById(int id) throws EmployeeWithTheIDNotPresentException;
	Employee addNewEmployee(Employee employee) throws EmployeeWithTheIDAlreadyPresentException;
	void deleteEmployee(int id) throws EmployeeWithTheIDNotPresentException;
	Employee updateEmployee(Employee employee) throws EmployeeWithTheIDNotPresentException;

}
