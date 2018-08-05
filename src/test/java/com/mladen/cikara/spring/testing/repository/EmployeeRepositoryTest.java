package com.mladen.cikara.spring.testing.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.mladen.cikara.spring.testing.entity.Employee;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * This test test only persistence layer. TestEntityManager is entity manager only for testing what
 * we can set up with initial values and then run tests with repository classes.
 *
 * Console output will show sql statements.
 *
 * @author mladen
 *
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EmployeeRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private EmployeeRepository employeeRepository;

  @Test
  public void whenFindByName_thenReturnEmployee() {
    // given
    final Employee alex = new Employee();
    alex.setName("alex");

    this.entityManager.persist(alex);
    this.entityManager.flush();

    // when
    final Optional<Employee> found = this.employeeRepository.findByName("alex");

    assertThat(found.isPresent()).isEqualTo(true);

    // then
    assertThat(found.get().getName()).isEqualTo(alex.getName());
  }
}
