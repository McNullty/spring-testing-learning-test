package com.mladen.cikara.spring.testing.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.mladen.cikara.spring.testing.entity.Employee;
import com.mladen.cikara.spring.testing.repository.EmployeeRepository;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Unit test that tests service layer. Spring context doesn't needs to be constructed.
 * 
 * @TestConfiguration is used to autowire EmployeeService and Mock EmployeeRepository will be
 *                    injected.
 * 
 * @author mladen
 *
 */
@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {

  @TestConfiguration
  static class EmployeeServiceImplTestContextConfiguration {

    @Bean
    public EmployeeService employeeService() {
      return new EmployeeServiceImpl();
    }
  }

  @Autowired
  private EmployeeService employeeService;

  @MockBean
  private EmployeeRepository employeeRepository;

  @BeforeEach
  public void setUp() {
    final Employee alex = new Employee();
    alex.setName("alex");

    Mockito.when(this.employeeRepository.findByName(alex.getName()))
        .thenReturn(Optional.of(alex));
  }

  @Test
  public void whenValidName_thenEmployeeShouldBeFound() {
    final String name = "alex";
    final Employee found = this.employeeService.getEmployeeByName(name);

    assertThat(found.getName())
        .isEqualTo(name);
  }
}
