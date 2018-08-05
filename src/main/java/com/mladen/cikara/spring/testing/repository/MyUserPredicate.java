package com.mladen.cikara.spring.testing.repository;

import com.mladen.cikara.spring.testing.entity.MyUser;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;

public class MyUserPredicate {

  public static boolean isNumeric(final String str) {
    try {
      Integer.parseInt(str);
    } catch (final NumberFormatException e) {
      return false;
    }
    return true;
  }

  private SearchCriteria criteria;

  public MyUserPredicate(final SearchCriteria criteria) {
    this.criteria = criteria;
  }

  public SearchCriteria getCriteria() {
    return this.criteria;
  }

  public BooleanExpression getPredicate() {
    final PathBuilder<MyUser> entityPath = new PathBuilder<>(MyUser.class, "myUser");

    if (isNumeric(this.criteria.getValue().toString())) {
      final NumberPath<Integer> path = entityPath.getNumber(this.criteria.getKey(), Integer.class);
      final int value = Integer.parseInt(this.criteria.getValue().toString());
      switch (this.criteria.getOperation()) {
      case ":":
        return path.eq(value);
      case ">":
        return path.goe(value);
      case "<":
        return path.loe(value);
      }
    } else {
      final StringPath path = entityPath.getString(this.criteria.getKey());
      if (this.criteria.getOperation().equalsIgnoreCase(":")) {
        return path.containsIgnoreCase(this.criteria.getValue().toString());
      }
    }
    return null;
  }

  public void setCriteria(final SearchCriteria criteria) {
    this.criteria = criteria;
  }
}