package com.cab;



import java.util.Optional;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.emp.entity.EmployeeDTO;
import com.emp.exception.EmployeeException;
import com.emp.repo.EmployeeRepository;
import com.emp.service.EmployeeService;
import com.emp.service.EmployeeServiceImpl;


@SpringBootTest
class EmployeeDTOBackendApplicationTests {

	@Mock
	EmployeeRepository employeeRepository;
	
	@Mock
	EmployeeService employeeService;
	
	@InjectMocks
	EmployeeServiceImpl employeeServices=new EmployeeServiceImpl();
	
	
	@Test
	 public void addEmployeeValid() throws EmployeeException {
		//Employee emps=new Employee(129,"Raja","Hcl",9090009090l,9000);
		 com.emp.dto.EmployeeDTO emp = new com.emp.dto.EmployeeDTO(129,"Raja","Hcl",9090009090l,9000);
			
        Mockito.when(employeeRepository.findById(129)).thenReturn(Optional.of(emps));
          Integer i = employeeService.addEmployee(emp);
	 
          
	Assertions.assertEquals(i, emps.getId());

}
	@Test
	public void addEmployeeInValid() throws EmployeeException {
	    com.emp.dto.EmployeeDTO emp = new com.emp.dto.EmployeeDTO(2, "Dharani", "Hcl", 9090909090L, 9000);

	    Mockito.when(employeeRepository.findById(2)).thenReturn(Optional.of(new EmployeeDTO(city)));

	    EmployeeException exception = Assertions.assertThrows(EmployeeException.class, () -> {
	        employeeService.addEmployee(emp);
	    });

	    Assertions.assertEquals("SERVICE_ALREADY_EXISTS", exception.getMessage());
	}

	@Test
	public void getEmployeeValid()throws EmployeeException{

		Integer id=2;
		
Mockito.when(employeeRepository.findById(2)).thenReturn(Optional.empty());

  EmployeeException ex= Assertions.assertThrows(EmployeeException.class, ()->employeeService.getEmployee(id));
		  //employeeService.getEmployee(2);

   Assertions.assertEquals("SERVICE_EMP_NOT_EXISTS", ex.getMessage());


		
	}
	@Test
	public void getEmployeeInvalid() throws EmployeeException {
	    int invalidId = 999;
	    Mockito.when(employeeRepository.findById(invalidId)).thenReturn(Optional.empty());

	    EmployeeException exception = Assertions.assertThrows(EmployeeException.class, () -> {
	        employeeService.getEmployee(invalidId);
	    });

	    Assertions.assertEquals("SERVICE_EMP_NOT_EXISTS", exception.getMessage());
	}

}