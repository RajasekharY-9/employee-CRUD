package com.emp.service;

import com.emp.dto.CityDTO;
import com.emp.entity.City;
import com.emp.entity.EmployeeDTO;
import com.emp.exception.EmployeeException;
import com.emp.repo.CityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CityServiceImpl implements CityService{
    @Autowired
    CityRepository cityRepository;
    
    @Autowired
    EmployeeServiceImpl employeeService;
    @Override
    public void addCity(CityDTO cityDTO) throws EmployeeException {
        City city = cityRepository.findByCityName(cityDTO.getCityName());
        if(city!=null){
            throw new EmployeeException("SERVICE.CITY_EXISTS");
        }
        if(city==null) {
            City c = converttoEntity(cityDTO);
            cityRepository.save(c);
        }
    }

    @Override
    public CityDTO getCity(String name) throws EmployeeException {
        City city = cityRepository.findByCityName(name);
        if(city==null){
            throw new EmployeeException("SERVICE.CITY_DOESNT_EXISTS");
        }
        CityDTO cityDTO = convertoDTO(city);
        return cityDTO;
    }

    @Override
    public List<CityDTO> getAllCities() throws EmployeeException {
        List<City> cities = cityRepository.findAll();
        if(cities.isEmpty()){
            throw new EmployeeException("SERVICE.No_Cities_FOUND");
        }
        List<CityDTO> cityDTOList = new ArrayList<>();
        for(City c:cities){
            CityDTO cs=convertoDTO(c);
            cityDTOList.add(cs);}
        return cityDTOList;
    }

    @Override
    public void deleteCity(String name) throws EmployeeException {
        City city = cityRepository.findByCityName(name);
        if(city==null){
            throw new EmployeeException("SERVICE.No_Cities_FOUND");
        }
        cityRepository.delete(city);
    }

    @Override
    public List<com.emp.dto.EmployeeDTO> getByEmployeeByCityId(Integer id) {
        List<EmployeeDTO> emps = cityRepository.getByEmployee(id);
        List<com.emp.dto.EmployeeDTO> employeeDTOList = new ArrayList<>();
        for(EmployeeDTO e:emps) {
            com.emp.dto.EmployeeDTO employeeDTO = employeeService.convertoEmpDTO(e);
            employeeDTOList.add(employeeDTO);
        }
        return employeeDTOList;
    }

    @Override
    public CityDTO updateCity(CityDTO cityDTO) throws EmployeeException {
        Optional<City> city = cityRepository.findById(cityDTO.getId());
        if(city.isEmpty()){
            throw new EmployeeException("SERVICE.No_Cities_FOUND");
        }
        City c=city.get();
c.setCityName(cityDTO.getCityName());
c.setZipCode(cityDTO.getZipCode());
c.setState(cityDTO.getState());

        List<EmployeeDTO> employees = new ArrayList<>();
        for(EmployeeDTO e:cityDTO.getEmployees()){
            EmployeeDTO emp = employeeService.convertoEntity(e);
            employees.add(emp);
        }
c.setEmployees(cityDTO.getEmployees());
CityDTO cd=convertoDTO( cityRepository.save(c));
        return cd;

    }

    public CityDTO convertoDTO(City city){
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setCityName(city.getCityName());
        cityDTO.setZipCode(city.getZipCode());
        cityDTO.setState(city.getState());
        return cityDTO;
    }
    public City converttoEntity(CityDTO cityDTO){
        City city = new City();
        city.setId(cityDTO.getId());
        city.setCityName(cityDTO.getCityName());
        city.setZipCode(cityDTO.getZipCode());
        city.setState(cityDTO.getState());
        return city;
    }

}
