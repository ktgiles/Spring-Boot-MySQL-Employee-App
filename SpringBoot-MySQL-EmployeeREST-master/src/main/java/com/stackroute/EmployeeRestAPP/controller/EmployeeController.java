package com.stackroute.EmployeeRestAPP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.EmployeeRestAPP.exceptions.EmployeeWithTheIDAlreadyPresentException;
import com.stackroute.EmployeeRestAPP.exceptions.EmployeeWithTheIDNotPresentException;
import com.stackroute.EmployeeRestAPP.model.Employee;
import com.stackroute.EmployeeRestAPP.service.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService; 
	
	
	@GetMapping(value = "/employees")
	public ResponseEntity<List<Employee>> getAllEmployeesHandler() {
		
		ResponseEntity<List<Employee>> responseEntity;		
		List<Employee> employees = employeeService.getAllEmployee();
		responseEntity = new ResponseEntity<List<Employee>>(employees,HttpStatus.OK);
		return responseEntity;
	}
	
	
	@PostMapping("/employees")
	public ResponseEntity<?> addEmployeeHander(@RequestBody Employee employee){
		
		ResponseEntity<?> responseEntity;
		try {
			Employee newEmployee = employeeService.addNewEmployee(employee);
			responseEntity = new ResponseEntity<Employee>(newEmployee,HttpStatus.CREATED);
		}catch(EmployeeWithTheIDAlreadyPresentException e) {
			responseEntity = new ResponseEntity<String>("Failed to store the employee: Duplicate Resource",HttpStatus.CONFLICT);
		}
		
		return responseEntity;
		
	}
	
	@GetMapping("/employees/{empId}")
	public ResponseEntity<?> getEmployeeByIdHandler(@PathVariable("empId") int id){
		
		ResponseEntity<?> responseEntity;
		
		try {
			Employee employee = employeeService.getEmployeeById(id);
			responseEntity = new ResponseEntity<Employee>(employee,HttpStatus.OK);
		}catch(EmployeeWithTheIDNotPresentException e) {
			responseEntity = new ResponseEntity<String>("Employee with the ID not found",HttpStatus.NOT_FOUND);
		}
		
		return responseEntity;
		
	}
	
	@PutMapping("/employees/{empId}")
	public ResponseEntity<?> updateEmployeeHandler(@RequestBody Employee emp){
	
		ResponseEntity<?> responseEntity;
	
		try {
			Employee employee = employeeService.getEmployeeById(emp.getId());

			employee.setFirstName(emp.getFirstName());
			employee.setSalary(emp.getSalary());
			employee.setDesignation(emp.getDesignation());
			
			emp = employeeService.updateEmployee(employee);
						
			responseEntity = new ResponseEntity<Employee>(employee, HttpStatus.OK);
			
		} catch (EmployeeWithTheIDNotPresentException e) {
			responseEntity = new ResponseEntity<String>("Employee with the ID not found", HttpStatus.NOT_FOUND);
		}

		return responseEntity;
	}
	
	@DeleteMapping("/employees/{empId}")
	public ResponseEntity<?> deleteEmployeeHandler(@PathVariable("empId") int id){
		
		ResponseEntity<?> responseEntity;
		
		try {
			employeeService.deleteEmployee(id);
		} catch (EmployeeWithTheIDNotPresentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		String response = "Employee " + id + " deleted.";

		responseEntity = new ResponseEntity<String>( response ,HttpStatus.OK);
	
	return responseEntity;
	
}
//		delete employee if it is present
//		else return the error response based on the exception thrown


}


//resource
//responsecode
//responseheaders

//ResponseEntity --- wraps resource, responsecode and response headers as a single object



