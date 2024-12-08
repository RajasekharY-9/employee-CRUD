package com.emp.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@NamedQuery(name="Employee.getEmployessByAllPhones", query="SELECT c FROM Employee c WHERE c.phoneNo = :phoneNo")
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String company;
	private Long phoneNo;
	private Integer salary;
	@ManyToOne
	@JoinColumn(name="city_id")
	@JsonBackReference
	private City city;
}