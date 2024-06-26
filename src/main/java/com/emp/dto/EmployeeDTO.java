package com.emp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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


}
