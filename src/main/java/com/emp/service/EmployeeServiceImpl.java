package com.emp.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.entity.Employee;
import com.emp.exception.EmployeeException;
import com.emp.repo.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public com.emp.dto.EmployeeDTO getEmployee(Integer id) throws EmployeeException {

		Optional<Employee> isExist = employeeRepository.findById(id);

		Employee employee = isExist.orElseThrow(() -> new EmployeeException("SERVICE_EMP_NOT_EXISTS"));
 com.emp.dto.EmployeeDTO emp= convertoEmpDTO(employee);

		return emp;
	}

	@Override
	public List<com.emp.dto.EmployeeDTO> getAllEmployees() throws EmployeeException {

		Iterable<Employee> employees = employeeRepository.findAll();

		List<com.emp.dto.EmployeeDTO> list = new ArrayList<>();
for(Employee emp:employees){
	com.emp.dto.EmployeeDTO e= convertoEmpDTO(emp);
	list.add(e);
}
		if (list.isEmpty()) {
			throw new EmployeeException("SERVICE_EMP_NOT_EXISTS");
		}

		return list;
	}

	@Override
	public Integer addEmployee(com.emp.dto.EmployeeDTO empdto) throws EmployeeException {
		Optional<Employee> isExist = employeeRepository.findByName(empdto.getName());
		if (isExist.isPresent()) {
			throw new EmployeeException("SERVICE_ALREADY_EXISTS");
		}
		Employee e= employeeRepository.save(convertoEntity(empdto));
return e.getId();
	}

	@Override
	public void deleteEmployee(Integer id) throws EmployeeException {

		Optional<Employee> isExist = employeeRepository.findById(id);

		isExist.orElseThrow(() -> new EmployeeException("SERVICE_EMP_NOT_EXISTS"));

		employeeRepository.deleteById(id);

	}

	@Override
	public com.emp.dto.EmployeeDTO getByName(String name) throws EmployeeException {
		Optional<Employee> emp = employeeRepository.findByName(name);
		 emp.orElseThrow(() -> new EmployeeException("SERVICE_EMP_NOT_EXISTS"));
    com.emp.dto.EmployeeDTO e= convertoEmpDTO(emp.get());
	return e;
	}

	@Override
	public com.emp.dto.EmployeeDTO getByPhoneNumber(Long phoneNo) throws EmployeeException {
		Optional<Employee> emp = employeeRepository.findByPhoneNo(phoneNo);
		 emp.orElseThrow(() -> new EmployeeException("SERVICE_EMP_NOT_EXISTS"));
	com.emp.dto.EmployeeDTO e= convertoEmpDTO(emp.get());
		return e;
	}


	@Override
	public void updateEmployee(Integer id, com.emp.dto.EmployeeDTO empdto) throws EmployeeException {
		Optional<Employee> emp = employeeRepository.findById(id);
		Employee emps = emp.orElseThrow(() -> new EmployeeException("SERVICE_EMP_NOT_EXISTS"));
		emps.setCompany(empdto.getCompany());
		emps.setName(empdto.getName());
		emps.setPhoneNo(empdto.getPhoneNo());
		emps.setSalary(empdto.getSalary());
	}


	@Override
	public com.emp.dto.EmployeeDTO getByCompanyAndName(String company, String name) throws EmployeeException {
		Employee emp = employeeRepository.getByCompanyAndName(company, name);
		if (emp == null) {
			throw new EmployeeException("Employee Not Found !!!");
		}
		com.emp.dto.EmployeeDTO employeeDTO = convertoEmpDTO(emp);

		return employeeDTO;
	}

	@Override
	public List<com.emp.dto.EmployeeDTO> getEmployessByAllPhones(Long phoneNo) throws EmployeeException {
		List<com.emp.dto.EmployeeDTO> employeeDTOS = getEmployessByAllPhones(phoneNo);
if(employeeDTOS.isEmpty()) {
	throw new EmployeeException("Employee Not Found !!!");
}
List<Employee> emps=new ArrayList<>();
for(com.emp.dto.EmployeeDTO emp:employeeDTOS) {
	Employee e=convertoEntity(emp);
	emps.add(e);
}
List<com.emp.dto.EmployeeDTO> edtos=new ArrayList<>();
for(Employee e:emps) {
	com.emp.dto.EmployeeDTO edto=convertoEmpDTO(e);
	edtos.add(edto);
}

		return edtos;
	}

	public com.emp.dto.EmployeeDTO convertoEmpDTO(Employee emp) {
		com.emp.dto.EmployeeDTO employeeDTO = new com.emp.dto.EmployeeDTO();
		employeeDTO.setId(emp.getId());
		employeeDTO.setName(emp.getName());
		employeeDTO.setCompany(emp.getCompany());
		employeeDTO.setPhoneNo(emp.getPhoneNo());
		employeeDTO.setSalary(emp.getSalary());
		return employeeDTO;


	}

	public Employee convertoEntity(com.emp.dto.EmployeeDTO empdto) {

		Employee emp = new Employee();
		emp.setId(empdto.getId());
		emp.setName(empdto.getName());
		emp.setCompany(empdto.getCompany());
		emp.setPhoneNo(empdto.getPhoneNo());
		emp.setSalary(empdto.getSalary());
		return emp;

	}
}