package com.emp.repo;

import com.emp.entity.City;
import com.emp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {

    public City findByCityName(String cityName);

    @Query("SELECT c FROM City c WHERE c.state = :state")
    public City getbystate(String state);

    //@Query("SELECT e from city c inner join employee e on c.id= e.city_id where e.id = :id")
    @Query("SELECT E FROM Employee E where E.city.id = :id")
    List<Employee> getByEmployee(Integer id);

}
