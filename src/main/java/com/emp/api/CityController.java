package com.emp.api;

import com.emp.dto.CityDTO;
import com.emp.dto.EmployeeDTO;
import com.emp.exception.EmployeeException;
import com.emp.service.CityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/city")
public class CityController {

    @Autowired
    CityService cityService;

    @PostMapping("/add")
    //http://localhost:8089/
    public ResponseEntity<String> addCity(@Valid @RequestBody CityDTO cityDTO) throws EmployeeException{
        cityService.addCity(cityDTO);
        return ResponseEntity.ok("City Added Successfully");

    }
    @GetMapping("/{name}")
    //http://localhost:8089/city/Bangalore
    public ResponseEntity<CityDTO> getCity(@PathVariable String name) throws EmployeeException {
        CityDTO cityDTO = cityService.getCity(name);
        return ResponseEntity.ok(cityDTO);

    }
    @GetMapping("/all")
    //http://localhost:8089/city/all
    public ResponseEntity<List<CityDTO>> getAllCities() throws EmployeeException{
List<CityDTO> list=cityService.getAllCities();
        return new ResponseEntity<>( list, HttpStatus.OK);
    }
    @DeleteMapping("/{name}")
    //http://localhost:8089/city/Bangalore
    public ResponseEntity<String> deleteCity(@PathVariable String name) throws EmployeeException{
        cityService.deleteCity(name);
        return ResponseEntity.ok("City Deleted Successfully");
    }

    @GetMapping("/employee/{id}")
    //http://localhost:8089/city/employee/1
    List<EmployeeDTO> getByEmployeeByCityId(Integer id){
       return cityService.getByEmployeeByCityId(id);
    }

    @PutMapping("/update")
    //http://localhost:8089/city/update
    public ResponseEntity<CityDTO> updateCity(@Valid @RequestBody CityDTO cityDTO) throws EmployeeException{
       CityDTO c= cityService.updateCity(cityDTO);
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
}
