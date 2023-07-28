package com.cts.swbdd.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.swbdd.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
