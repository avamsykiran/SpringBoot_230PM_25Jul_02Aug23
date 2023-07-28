package com.cts.swbdd.service;

import java.util.List;

import com.cts.swbdd.entity.Employee;

public interface EmployeeService {
	Employee add(Employee emp);
	Employee update(Employee emp);
	void delete(long empId);
	Employee getEmployee(long empID);
	List<Employee> getAll();
}
