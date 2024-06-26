package com.emp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class EmployeeDTO {

	//@NotNull(message="employee.id.notpresent")
    private Integer id;
	
	@NotNull(message="employee.name.notpresent")
	@Pattern(regexp="[A-Za-z]([A-Za-z]+)*",message="employee.name.invalid")
	private String name;
	
	@NotNull(message="employee.company.notpresent")
	@Pattern(regexp="[A-Za-z]([A-Za-z]+)*",message="employee.company.invalid")
	private String company;
	
	
	
	private Long phoneNo;
	
	@NotNull(message="employee.salary.notpresent")
	private Integer salary;

	public EmployeeDTO() {
		super();
		
	}

	
	public EmployeeDTO(Integer id,
			 String name,
			 String company,
			 Long phoneNo,
			Integer salary) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
		this.phoneNo = phoneNo;
		this.salary = salary;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	
	
	
	
	
}
