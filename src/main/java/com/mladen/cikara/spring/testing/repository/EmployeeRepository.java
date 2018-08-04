package com.mladen.cikara.spring.testing.repository;

import com.mladen.cikara.spring.testing.entity.Employee;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  public Optional<Employee> findByName(String name);

}
