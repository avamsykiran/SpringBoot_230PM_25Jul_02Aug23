package com.cts.srbdd.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.srbdd.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
