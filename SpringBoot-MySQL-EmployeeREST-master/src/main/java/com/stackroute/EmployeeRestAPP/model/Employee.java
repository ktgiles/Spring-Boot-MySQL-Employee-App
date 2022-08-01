package com.stackroute.EmployeeRestAPP.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
	
	@Id
	private int id;
	
//	@Column(nullable = false,unique = false)
	private String firstName;
	private String designation;
	private String salary;
	public Employee(int id, String firstName, String designation, String salary) {
		this.id = id;
		this.firstName = firstName;
		this.designation = designation;
		this.salary = salary;
	}
	
	public Employee() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", designation=" + designation + ", salary=" + salary
				+ "]";
	}	

}
