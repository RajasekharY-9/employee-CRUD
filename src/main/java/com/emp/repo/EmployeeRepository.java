package com.emp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emp.entity.EmployeeDTO;

public interface EmployeeRepository extends JpaRepository<EmployeeDTO, Integer>{

	public Optional<EmployeeDTO> findByName(String name);
	@Query("SELECT c FROM Employee c WHERE c.company = :company AND c.name = :name")
	public EmployeeDTO getByCompanyAndName(String company, String name);

	//@Query("SELECT c.phoneNo FROM Employee c where c.phoneNo = :phoneNo")
	//@Query("select c from Employee c where phoneNo=?1")
	@Query("SELECT c FROM Employee c WHERE c.phoneNo = :phoneNo")
	Optional<EmployeeDTO> findByPhoneNo(@Param("phoneNo") Long phoneNo);



}
