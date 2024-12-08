package com.emp.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@ToString
@NoArgsConstructor
@NamedQuery(name="City.getCityByZipCode", query="SELECT c FROM City c WHERE c.zipCode = :zipCode")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="city_id")
    Integer id;
    String cityName;
    String zipCode;
    String state;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
     List<Employee> employees = new ArrayList<>();
}