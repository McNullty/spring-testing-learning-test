package com.mladen.cikara.spring.testing.service;

import com.mladen.cikara.spring.testing.entity.Employee;
import com.mladen.cikara.spring.testing.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Override
  public Employee getEmployeeByName(final String name) {
    return this.employeeRepository.findByName(name).get();
  }
}
