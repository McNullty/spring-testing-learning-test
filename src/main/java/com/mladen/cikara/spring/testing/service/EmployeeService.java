package com.mladen.cikara.spring.testing.service;

import com.mladen.cikara.spring.testing.entity.Employee;

public interface EmployeeService {

  Employee getEmployeeByName(String name);
}
