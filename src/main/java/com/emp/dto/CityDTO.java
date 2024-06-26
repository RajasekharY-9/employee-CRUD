package com.emp.dto;

import com.emp.entity.EmployeeDTO;

import java.util.List;

public class CityDTO {

    Integer id;
    String cityName;
    String zipCode;
    String state;

    List<EmployeeDTO> employees;

    public CityDTO() {
    }

    public CityDTO(Integer id, String cityName, String zipCode, String state, List<EmployeeDTO> employees) {
        this.id = id;
        this.cityName = cityName;
        this.zipCode = zipCode;
        this.state = state;
        this.employees = employees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }
}
