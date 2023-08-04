package com.cts.srbdd.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.srbdd.entity.Employee;
import com.cts.srbdd.exception.InvalidEmployeeDetailsException;
import com.cts.srbdd.service.EmployeeService;

@RestController //@Controller + @ResponseBody
@RequestMapping("/emps")
public class EmployeeRestApiController {

	@Autowired
	private EmployeeService empService;
	
	@GetMapping
	public ResponseEntity<List<Employee>> sendAllEmployeesList(){
		return ResponseEntity.ok(empService.getAll());
	}
	
	@GetMapping("/{empId}")
	public ResponseEntity<Employee> sendEmployeeById(@PathVariable("empId") Long empId){
		Employee emp = empService.getEmployee(empId);
		return emp==null? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(emp);
	}
	
	@DeleteMapping("/{empId}")
	public ResponseEntity<Void> deleteEmployeeById(@PathVariable("empId") Long empId){
		empService.delete(empId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody @Valid Employee emp,BindingResult bindingResult) throws InvalidEmployeeDetailsException{
		
		if(bindingResult.hasErrors()) {	
			throw new InvalidEmployeeDetailsException(bindingResult);
		}
		
		emp = empService.add(emp);
		return new ResponseEntity<>(emp, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid Employee emp,BindingResult bindingResult) throws InvalidEmployeeDetailsException{
		
		if(bindingResult.hasErrors()) {	
			throw new InvalidEmployeeDetailsException(bindingResult);
		}
		
		emp = empService.update(emp);
		return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
	}
}