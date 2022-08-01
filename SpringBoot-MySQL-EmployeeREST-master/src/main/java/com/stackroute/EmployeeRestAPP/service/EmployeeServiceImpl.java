package com.stackroute.EmployeeRestAPP.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.EmployeeRestAPP.exceptions.EmployeeWithTheIDAlreadyPresentException;
import com.stackroute.EmployeeRestAPP.exceptions.EmployeeWithTheIDNotPresentException;
import com.stackroute.EmployeeRestAPP.model.Employee;
import com.stackroute.EmployeeRestAPP.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) throws EmployeeWithTheIDNotPresentException {
		// TODO Auto-generated method stub
		
		Optional<Employee> optional = employeeRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		throw new EmployeeWithTheIDNotPresentException();
	}

	@Override
	public Employee addNewEmployee(Employee employee) throws EmployeeWithTheIDAlreadyPresentException {
		// TODO Auto-generated method stub
		Optional<Employee> optional = employeeRepository.findById(employee.getId());
		
		if(optional.isEmpty()) {
			employeeRepository.save(employee);
			return employee;
		}
		throw new EmployeeWithTheIDAlreadyPresentException();
	}
	
	@Override
	public Employee updateEmployee(Employee employee) throws EmployeeWithTheIDNotPresentException {
		// TODO Auto-generated method stub
		Optional<Employee> optional = employeeRepository.findById(employee.getId());
				
		if(optional.isPresent()) {
			employeeRepository.save(employee);
			return employee;
		}
		throw new EmployeeWithTheIDNotPresentException();
	}
			
		
//		check if the employee is present or not
//		if the employee is present update the employee and the return the updated employee
//		if the employee is not present throw the appropriate exception that describes the failure of update operation

	@Override
	public void deleteEmployee(int id) throws EmployeeWithTheIDNotPresentException {
		Optional<Employee> optional = employeeRepository.findById(id);
		if(optional.isPresent()) {
			Employee employee = employeeRepository.getById(id);
			employeeRepository.delete(employee);
		} else {
		throw new EmployeeWithTheIDNotPresentException();
		}
	}
		
//		check if the employee is present or not
//		if present delete the employee resource by using the appropriate jpa method
//		else throw the appropriate exception that describes the failure of delete operation
		
}



