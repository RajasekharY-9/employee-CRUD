package com.emp.service;

import com.emp.dto.CityDTO;
import com.emp.dto.EmployeeDTO;
import com.emp.entity.City;
import com.emp.entity.Employee;
import com.emp.exception.EmployeeException;
import com.emp.repo.CityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Override
    public void addCity(CityDTO cityDTO) throws EmployeeException {
        City city = cityRepository.findByCityName(cityDTO.getCityName());
        if (city != null) {
            throw new EmployeeException("SERVICE.CITY_EXISTS");
        }
        City newCity = convertToEntity(cityDTO);
        cityRepository.save(newCity);
    }
//What is this get city method
    @Override
    public CityDTO getCity(String name) throws EmployeeException {
        City city = cityRepository.findByCityName(name);
        if (city == null) {
            throw new EmployeeException("SERVICE.CITY_DOESNT_EXISTS");
        }
        return convertToDTO(city);
    }

    @Override
    public List<CityDTO> getAllCities() throws EmployeeException {
        List<City> cities = cityRepository.findAll();
        if (cities.isEmpty()) {
            throw new EmployeeException("SERVICE.No_Cities_FOUND");
        }
        return cities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteCity(String name) throws EmployeeException {
        City city = cityRepository.findByCityName(name);
        if (city == null) {
            throw new EmployeeException("SERVICE.No_Cities_FOUND");
        }
        cityRepository.delete(city);
    }

    @Override
    public List<EmployeeDTO> getByEmployeeByCityId(Integer id) {
        List<Employee> employees = cityRepository.getByEmployee(id);
        return employees.stream().map(employeeService::convertoEmpDTO).collect(Collectors.toList());
    }

    @Override
    public CityDTO updateCity(CityDTO cityDTO) throws EmployeeException {
        Optional<City> cityOpt = cityRepository.findById(cityDTO.getId());
        if (cityOpt.isEmpty()) {
            throw new EmployeeException("SERVICE.No_Cities_FOUND");
        }
        City city = cityOpt.get();
        city.setCityName(cityDTO.getCityName());
        city.setZipCode(cityDTO.getZipCode());
        city.setState(cityDTO.getState());

        List<Employee> employees = cityDTO.getEmployees().stream()
                .map(employeeService::convertoEntity)
                .peek(e -> e.setCity(city))
                .collect(Collectors.toList());

        city.setEmployees(employees);
        City updatedCity = cityRepository.save(city);
        return convertToDTO(updatedCity);
    }

    private CityDTO convertToDTO(City city) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setCityName(city.getCityName());
        cityDTO.setZipCode(city.getZipCode());
        cityDTO.setState(city.getState());

        List<EmployeeDTO> employeeDTOList = city.getEmployees().stream()
                .map(employeeService::convertoEmpDTO)
                .collect(Collectors.toList());

        cityDTO.setEmployees(employeeDTOList);
        return cityDTO;
    }

    private City convertToEntity(CityDTO cityDTO) {
        City city = new City();
        city.setId(cityDTO.getId());
        city.setCityName(cityDTO.getCityName());
        city.setZipCode(cityDTO.getZipCode());
        city.setState(cityDTO.getState());

        List<Employee> employees = cityDTO.getEmployees().stream()
                .map(employeeService::convertoEntity)
                .peek(e -> e.setCity(city))
                .collect(Collectors.toList());

        city.setEmployees(employees);
        return city;
    }
}
