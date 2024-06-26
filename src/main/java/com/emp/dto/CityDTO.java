package com.emp.dto;

import com.emp.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CityDTO {

    Integer id;
    String cityName;
    String zipCode;
    String state;

    List<Employee> employees;

}
