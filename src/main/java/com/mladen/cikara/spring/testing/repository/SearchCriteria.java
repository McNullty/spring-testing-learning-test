package com.mladen.cikara.spring.testing.repository;

public class SearchCriteria {

  private String key;
  private String operation;
  private Object value;

  public SearchCriteria() {

  }

  public SearchCriteria(final String key, final String operation, final Object value) {
    super();
    this.key = key;
    this.operation = operation;
    this.value = value;
  }

  public String getKey() {
    return this.key;
  }

  public String getOperation() {
    return this.operation;
  }

  public Object getValue() {
    return this.value;
  }

  public void setKey(final String key) {
    this.key = key;
  }

  public void setOperation(final String operation) {
    this.operation = operation;
  }

  public void setValue(final Object value) {
    this.value = value;
  }

}
