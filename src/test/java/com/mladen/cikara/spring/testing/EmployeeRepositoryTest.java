package com.mladen.cikara.spring.testing;

import static org.assertj.core.api.Assertions.assertThat;

import com.mladen.cikara.spring.testing.entity.Employee;
import com.mladen.cikara.spring.testing.repository.EmployeeRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
