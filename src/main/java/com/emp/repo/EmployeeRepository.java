package com.emp.repo;
import java.util.List;
import java.util.Optional;

import com.emp.dto.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.emp.entity.Employee;
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	public Optional<Employee> findByName(String name);
	@Query("SELECT c FROM Employee c WHERE c.company = :company AND c.name = :name")
	public Employee getByCompanyAndName(String company, String name);
	//@Query("SELECT c.phoneNo FROM Employee c where c.phoneNo = :phoneNo")
	//@Query("select c from Employee c where phoneNo=?1")
	@Query("SELECT c FROM Employee c WHERE c.phoneNo = :phoneNo")
	Employee findByPhoneNo(@Param("phoneNo") Long phoneNo);
	List<Employee> findByCityId(Integer cityId);
}