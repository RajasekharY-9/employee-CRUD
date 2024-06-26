package com.emp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQuery(name="City.getCityByZipCode", query="SELECT c FROM City c WHERE c.zipCode = :zipCode")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="city_id")
    Integer id;
    String cityName;
    String zipCode;
    String state;
    @OneToMany(mappedBy = "city",cascade = CascadeType.ALL)
            @JsonManagedReference
    List<EmployeeDTO> employees;

    public City() {
    }

    public City(Integer id, String cityName, String zipCode, String state, List<EmployeeDTO> employees) {
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
