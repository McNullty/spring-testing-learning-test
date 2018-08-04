package com.mladen.cikara.spring.testing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "person")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Size(min = 3, max = 20)
  private String name;

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public void setName(final String name) {
    this.name = name;
  }

}
