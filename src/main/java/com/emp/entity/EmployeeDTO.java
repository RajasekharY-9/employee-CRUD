package com.emp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@NamedQuery(name="Employee.getEmployessByAllPhones", query="SELECT c FROM Employee c WHERE c.phoneNo = :phoneNo")
public class EmployeeDTO {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	private String company;
	
	private Long phoneNo;
	
	private Integer salary;

	@ManyToOne
	@JoinColumn(name="city_id")
	@JsonBackReference
	private City city;


	public EmployeeDTO() {

	}

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", name='" + name + '\'' +
				", company='" + company + '\'' +
				", phoneNo=" + phoneNo +
				", salary=" + salary +
				", city=" + city +
				'}';
	}

	public EmployeeDTO(Integer id, String name, String company, Long phoneNo, Integer salary, City city) {
		this.id = id;
		this.name = name;
		this.company = company;
		this.phoneNo = phoneNo;
		this.salary = salary;
		this.city = city;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCompany() {
		return company;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public Integer getSalary() {
		return salary;
	}

	public City getCity() {
		return city;
	}
}
