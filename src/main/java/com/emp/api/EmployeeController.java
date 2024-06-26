package com.emp.api;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.dto.EmployeeDTO;
import com.emp.exception.EmployeeException;
import com.emp.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("api")
@CrossOrigin("http://localhost:3000")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private Environment env;
	

	//http://localhost:8089/api/emp/get/2
	@GetMapping("emp/get/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer id) throws EmployeeException{
		EmployeeDTO emp=employeeService.getEmployee(id);
		
		return new ResponseEntity<>(emp,HttpStatus.OK);
		
		
	}
	
	
		
	//http://localhost:8089/api/emp/all
	@GetMapping("emp/all")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees()throws EmployeeException{
		
		List<EmployeeDTO> em=employeeService.getAllEmployees();
		
		
		return new ResponseEntity<>(em,HttpStatus.OK);
		
	}
	//http://localhost:8089/api/emp/add
	@PostMapping("/emp/add")
	public ResponseEntity<String> addEmployee(@RequestBody @Valid EmployeeDTO empdto)throws EmployeeException{
		
		employeeService.addEmployee(empdto);
		String msg=env.getProperty("API_SUCCESS");
		
		
		return new ResponseEntity<>(msg,HttpStatus.CREATED);
		
	}
	//http://localhost:8089/api/emp/del/5
	@DeleteMapping("/emp/del/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id)throws EmployeeException{
		
		employeeService.deleteEmployee(id);
String msg=env.getProperty("API_DELETED");
		
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
		
		
	}
	//http://localhost:8089/api/emp/upd/1
	@PutMapping("/emp/upd/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable Integer id,@RequestBody EmployeeDTO emp)throws EmployeeException{
		employeeService.updateEmployee(id, emp);
String msg=env.getProperty("API_UPDATED");
		
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
		
	}
	
	@GetMapping("/emp/name/{name}")
	public ResponseEntity<EmployeeDTO> getByName(@PathVariable String name)throws EmployeeException{
		
		EmployeeDTO e=employeeService.getByName(name);
		
		return new ResponseEntity<>(e,HttpStatus.OK);
		
	}
	//http://localhost:8089/api/employees/phoneNo/6078879890
	@GetMapping("/employees/phoneNo/{phoneNo}")
	public ResponseEntity<EmployeeDTO> getByPhoneNumber(@PathVariable("phoneNo") Long phoneNo) throws EmployeeException {
	    
		EmployeeDTO e = employeeService.getByPhoneNumber(phoneNo);
	        return ResponseEntity.ok(e);
	    
	}

	@GetMapping("/emp-phone-name/{company}/{name}")
	public ResponseEntity<EmployeeDTO> getByCompanyAndName(@PathVariable String company,@PathVariable String name) throws EmployeeException{
		EmployeeDTO e=employeeService.getByCompanyAndName(company, name);
return new ResponseEntity<>(e,HttpStatus.OK);
	}


	@GetMapping("/emp-phone/{phoneNo}")
	public ResponseEntity<List<EmployeeDTO>> getEmployessByAllPhones(@PathVariable Long phoneNo) throws EmployeeException{
		List<EmployeeDTO> emps=employeeService.getEmployessByAllPhones(phoneNo);
		return new ResponseEntity<>(emps,HttpStatus.OK);
	}
	
	
	

}
