package com.emp.service;
import com.emp.dto.CityDTO;
import com.emp.dto.EmployeeDTO;
import com.emp.exception.EmployeeException;
import java.util.List;
public interface CityService  {
    public void addCity(CityDTO cityDTO) throws EmployeeException;
    public CityDTO getCity(String name) throws EmployeeException;
    public List<CityDTO> getAllCities() throws EmployeeException;
public void deleteCity(String name) throws EmployeeException;
   List<EmployeeDTO> getByEmployeeByCityId(Integer id);
  CityDTO updateCity(CityDTO cityDTO) throws EmployeeException;
}