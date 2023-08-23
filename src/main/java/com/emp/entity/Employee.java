package com.emp.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String company;
	
	private Long phoneNo;
	
	private Integer salary;

	public Employee() {
		super();
		
	}

	public Employee(Integer id, String name, String company, Long phoneNo, Integer salary) {
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

	@Override
	public int hashCode() {
		return Objects.hash(company, id, name, phoneNo, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(company, other.company) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(phoneNo, other.phoneNo)
				&& Objects.equals(salary, other.salary);
	}
	
	
	
	
	
	
	
	
	
}
