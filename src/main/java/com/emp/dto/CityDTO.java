package com.emp.dto;
import com.emp.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Data
@NoArgsConstructor
@ToString
public class CityDTO {
    Integer id;
    String cityName;
    String zipCode;
    String state;
    List<EmployeeDTO> employees;
}