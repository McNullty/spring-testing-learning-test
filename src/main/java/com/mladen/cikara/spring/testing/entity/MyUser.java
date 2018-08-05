package com.mladen.cikara.spring.testing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// used for Querydsl test

@Entity
public class MyUser {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String firstName;

  private String lastName;

  private String email;

  private int age;

  public MyUser() {
    super();
  }

  public MyUser(final String firstName, final String lastName, final String email, final int age) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.age = age;
  }

  //

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final MyUser user = (MyUser) obj;
    if (!this.email.equals(user.email)) {
      return false;
    }
    return true;
  }

  public int getAge() {
    return this.age;
  }

  public String getEmail() {
    return this.email;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public Long getId() {
    return this.id;
  }

  public String getLastName() {
    return this.lastName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (this.email == null ? 0 : this.email.hashCode());
    return result;
  }

  public void setAge(final int age) {
    this.age = age;
  }

  public void setEmail(final String username) {
    this.email = username;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("MyUser [firstName=").append(this.firstName).append("]").append("[lastName=")
        .append(this.lastName).append("]").append("[username").append(this.email).append("]");
    return builder.toString();
  }

}