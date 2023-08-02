package com.cts.srbdd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.srbdd.entity.Employee;
import com.cts.srbdd.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo empRepo;
	
	@Override
	public Employee add(Employee emp) {
		emp = empRepo.save(emp);
		return emp;
	}

	@Override
	public Employee update(Employee emp) {
		if(empRepo.existsById(emp.getEmpId())) {
			emp = empRepo.save(emp);
		}
		return emp;
	}

	@Override
	public void delete(long empId) {
		empRepo.deleteById(empId);
	}

	@Override
	public Employee getEmployee(long empID) {
		return empRepo.findById(empID).orElse(null);
	}

	@Override
	public List<Employee> getAll() {
		return empRepo.findAll();
	}

}
