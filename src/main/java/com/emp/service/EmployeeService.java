package com.emp.service;
import java.util.List;
import com.emp.dto.EmployeeDTO;
import com.emp.exception.EmployeeException;
public interface EmployeeService {
	public EmployeeDTO getEmployee(Integer id) throws EmployeeException;
	public EmployeeDTO getByCompanyAndName(String company, String name) throws EmployeeException;
	public List<EmployeeDTO> getAllEmployees()throws EmployeeException;
	public Integer addEmployee(EmployeeDTO empdto)throws EmployeeException;
	public void deleteEmployee(Integer id)throws EmployeeException;
	public void updateEmployee(Integer id,EmployeeDTO emp)throws EmployeeException;
	public EmployeeDTO getByName(String name)throws EmployeeException;
	public EmployeeDTO getByPhoneNumber(Long phoneNo)throws EmployeeException;
	//apply pagenation and sorting
}