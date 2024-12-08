package com.emp.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.emp.dto.EmployeeDTO;
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
	public EmployeeDTO getEmployee(Integer id) throws EmployeeException {
		Optional<Employee> isExist = employeeRepository.findById(id);
		Employee employee = isExist.orElseThrow(() -> new EmployeeException("SERVICE_EMP_NOT_EXISTS"));
		return convertoEmpDTO(employee);
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() throws EmployeeException {
		Iterable<Employee> employees = employeeRepository.findAll();
		List<EmployeeDTO> list = new ArrayList<>();
		for (Employee emp : employees) {
			EmployeeDTO e = convertoEmpDTO(emp);
			list.add(e);
		}
		if (list.isEmpty()) {
			throw new EmployeeException("SERVICE_EMP_NOT_EXISTS");
		}
		return list;
	}

	@Override
	public Integer addEmployee(EmployeeDTO empdto) throws EmployeeException {
		Optional<Employee> isExist = employeeRepository.findByName(empdto.getName());
		if (isExist.isPresent()) {
			throw new EmployeeException("SERVICE_ALREADY_EXISTS");
		}
		Employee e = employeeRepository.save(convertoEntity(empdto));
		return e.getId();
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
		emp.orElseThrow(() -> new EmployeeException("SERVICE_EMP_NOT_EXISTS"));
		return convertoEmpDTO(emp.get());
	}

	@Override
	public EmployeeDTO getByPhoneNumber(Long phoneNo) throws EmployeeException {
		Employee emp = employeeRepository.findByPhoneNo(phoneNo);
		if(emp==null){
			throw new EmployeeException("SERVICE_EMP_NOT_EXISTS");
		};
		return convertoEmpDTO(emp);
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

	@Override
	public EmployeeDTO getByCompanyAndName(String company, String name) throws EmployeeException {
		Employee emp = employeeRepository.getByCompanyAndName(company, name);
		if (emp == null) {
			throw new EmployeeException("Employee Not Found !!!");
		}
		return convertoEmpDTO(emp);
	}



	public EmployeeDTO convertoEmpDTO(Employee emp) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId(emp.getId());
		employeeDTO.setName(emp.getName());
		employeeDTO.setCompany(emp.getCompany());
		employeeDTO.setPhoneNo(emp.getPhoneNo());
		employeeDTO.setSalary(emp.getSalary());
		return employeeDTO;
	}

	public Employee convertoEntity(EmployeeDTO empdto) {
		Employee emp = new Employee();
		emp.setId(empdto.getId());
		emp.setName(empdto.getName());
		emp.setCompany(empdto.getCompany());
		emp.setPhoneNo(empdto.getPhoneNo());
		emp.setSalary(empdto.getSalary());
		return emp;
	}
}