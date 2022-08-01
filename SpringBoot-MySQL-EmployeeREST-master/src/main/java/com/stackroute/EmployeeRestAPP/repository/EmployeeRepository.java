package com.stackroute.EmployeeRestAPP.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.EmployeeRestAPP.model.Employee;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}

//1.  specify custom implementations in jpa repository
