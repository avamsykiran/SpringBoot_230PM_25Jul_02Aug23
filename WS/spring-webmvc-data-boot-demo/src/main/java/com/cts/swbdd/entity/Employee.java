package com.cts.swbdd.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="emps")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="eid")
	private Long empId;

	@Column(name="fnm",nullable = false)
	@NotBlank(message = "Full Name is a mandatory field and can not be blank")
	private String fullName;
	
	@Column(name="sal",nullable = false)
	@NotNull(message = "Salary is a mandatory field")
	@Min(value = 12000,message = "Salary is expected to be 12000 minimum")
	@Max(value = 1200000,message = "Salary is expected to be 1200000 maximum")
	private Double salary;
	
	@Column(name="jdt",nullable=false)
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message = "Join Date is a mandatory field")
	@PastOrPresent(message = "Join date can not be a future date")
	private LocalDate joinDate;
	
	@Column(name="isc",nullable = false)
	private Boolean isContract;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(@NotBlank(message = "Full Name is a mandatory field and can not be blank") String fullName,
			@NotNull(message = "Salary is a mandatory field") @Min(value = 12000, message = "Salary is expected to be 12000 minimum") @Max(value = 1200000, message = "Salary is expected to be 1200000 maximum") Double salary,
			@NotNull(message = "Join Date is a mandatory field") @PastOrPresent(message = "Join date can not be a future date") LocalDate joinDate,
			Boolean isContract) {
		super();
		this.fullName = fullName;
		this.salary = salary;
		this.joinDate = joinDate;
		this.isContract = isContract;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public LocalDate getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}

	public Boolean getIsContract() {
		return isContract;
	}

	public void setIsContract(Boolean isContract) {
		this.isContract = isContract;
	}
	
	
}
