package com.emp.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.dto.EmployeeDTO;
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
	public EmployeeDTO getEmployee(Integer id) throws EmployeeException {

		Optional<Employee> isExist = employeeRepository.findById(id);

		Employee employee = isExist.orElseThrow(() -> new EmployeeException("SERVICE_EMP_NOT_EXISTS"));

		EmployeeDTO emp = new EmployeeDTO();

		emp.setId(employee.getId());
		emp.setName(employee.getName());
		emp.setPhoneNo(employee.getPhoneNo());
		emp.setCompany(employee.getCompany());
		emp.setSalary(employee.getSalary());

		return emp;
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() throws EmployeeException {

		Iterable<Employee> employees = employeeRepository.findAll();

		List<EmployeeDTO> list = new ArrayList<>();

		employees.forEach(emp -> {

			EmployeeDTO employee = new EmployeeDTO();

			employee.setId(emp.getId());
			employee.setName(emp.getName());
			employee.setPhoneNo(emp.getPhoneNo());
			employee.setCompany(emp.getCompany());
			employee.setSalary(emp.getSalary());

			list.add(employee);
		});
		if (list.isEmpty()) {
			throw new EmployeeException("SERVICE_EMP_NOT_EXISTS");
		}

		return list;
	}

	@Override
	public Integer addEmployee(EmployeeDTO empdto) throws EmployeeException {

		Optional<Employee> isExist = employeeRepository.findById(empdto.getId());
	if(isExist.isPresent()) {
		throw new EmployeeException("SERVICE_ALREADY_EXISTS");
	}
		
              	
		
		 Employee emp=new Employee();
		emp.setId(empdto.getId());
		emp.setName(empdto.getName());
		emp.setCompany(empdto.getCompany());
		emp.setPhoneNo(empdto.getPhoneNo());
		emp.setSalary(empdto.getSalary());
		employeeRepository.save(emp);
		return emp.getId();

	}

	@Override
	public void deleteEmployee(Integer id) throws EmployeeException {

		Optional<Employee> isExist = employeeRepository.findById(id);

		isExist.orElseThrow(() -> new EmployeeException("SERVICE_EMP_NOT_EXISTS"));

		employeeRepository.deleteById(id);

	}

	@Override
	public EmployeeDTO getByName(String name) throws EmployeeException {

		Optional<Employee> emp = employeeRepository.findByName(name);
		Employee e1=emp.orElseThrow(() -> new EmployeeException("SERVICE_EMP_NOT_EXISTS"));

		EmployeeDTO e=new EmployeeDTO();
	
		e.setId(e1.getId());
		e.setCompany(e1.getCompany());
		e.setName(e1.getName());
		e.setPhoneNo(e1.getPhoneNo());
		e.setSalary(e1.getSalary());
		
		
		return e;
	}

	@Override
	public EmployeeDTO getByPhoneNumber(Long phoneNo) throws EmployeeException {

		Optional<Employee> emp = employeeRepository.findByPhoneNo(phoneNo);
		Employee e1=emp.orElseThrow(() -> new EmployeeException("SERVICE_EMP_NOT_EXISTS"));
		
		EmployeeDTO e=new EmployeeDTO();
		
		e.setId(e1.getId());
		e.setCompany(e1.getCompany());
		e.setName(e1.getName());
		e.setPhoneNo(e1.getPhoneNo());
		e.setSalary(e1.getSalary());
		
		return e;
		
		
		
	}

	

	@Override
	public void updateEmployee(Integer id, EmployeeDTO empdto) throws EmployeeException {

		Optional<Employee> emp = employeeRepository.findById(id);

		Employee emps = emp.orElseThrow(() -> new EmployeeException("SERVICE_EMP_NOT_EXISTS"));

		emps.setCompany(empdto.getCompany());
		emps.setName(empdto.getName());

		emps.setPhoneNo(empdto.getPhoneNo());
		emps.setSalary(empdto.getSalary());

	}

}
