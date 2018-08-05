package com.mladen.cikara.spring.testing.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.mladen.cikara.spring.testing.entity.MyUser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MyUserRepositoryQuerydslTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private MyUserRepository repo;

  private MyUser userJohn;

  private MyUser userTom;

  @Test
  public void givenFirstAndLastName_whenGettingListOfUsers_thenCorrect() {
    final MyUserPredicatesBuilder builder =
        new MyUserPredicatesBuilder().with("firstName", ":", "john").with("lastName", ":", "doe");

    final Iterable<MyUser> results = this.repo.findAll(builder.build());

    assertThat(results).contains(this.userJohn);
    assertThat(results).doesNotContain(this.userTom);
  }

  @Test
  public void givenLast_whenGettingListOfUsers_thenCorrect() {
    final MyUserPredicatesBuilder builder =
        new MyUserPredicatesBuilder().with("lastName", ":", "doe");

    final Iterable<MyUser> results = this.repo.findAll(builder.build());
    assertThat(results).containsExactlyInAnyOrder(this.userJohn, this.userTom);
  }

  @Test
  public void givenLastAndAge_whenGettingListOfUsers_thenCorrect() {
    final MyUserPredicatesBuilder builder =
        new MyUserPredicatesBuilder().with("lastName", ":", "doe").with("age", ">", "25");

    final Iterable<MyUser> results = this.repo.findAll(builder.build());

    assertThat(results).contains(this.userTom);
    assertThat(results).doesNotContain(this.userJohn);
  }

  @Test
  public void givenPartialFirst_whenGettingListOfUsers_thenCorrect() {
    final MyUserPredicatesBuilder builder =
        new MyUserPredicatesBuilder().with("firstName", ":", "jo");

    final Iterable<MyUser> results = this.repo.findAll(builder.build());

    assertThat(results).contains(this.userJohn);
    assertThat(results).doesNotContain(this.userTom);
  }

  @Test
  public void givenWrongFirstAndLast_whenGettingListOfUsers_thenCorrect() {
    final MyUserPredicatesBuilder builder =
        new MyUserPredicatesBuilder().with("firstName", ":", "adam").with("lastName", ":", "fox");

    final Iterable<MyUser> results = this.repo.findAll(builder.build());
    assertThat(results).isEmpty();
  }

  @BeforeEach
  public void setup() {
    this.userJohn = new MyUser();
    this.userJohn.setFirstName("john");
    this.userJohn.setLastName("doe");
    this.userJohn.setEmail("john@doe.com");
    this.userJohn.setAge(22);
    this.entityManager.persist(this.userJohn);
    this.entityManager.flush();

    this.userTom = new MyUser();
    this.userTom.setFirstName("tom");
    this.userTom.setLastName("doe");
    this.userTom.setEmail("tom@doe.com");
    this.userTom.setAge(26);
    this.entityManager.persist(this.userTom);
    this.entityManager.flush();
  }

}
