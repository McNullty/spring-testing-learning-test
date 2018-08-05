package com.mladen.cikara.spring.testing.repository;

import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.ArrayList;
import java.util.List;

public final class MyUserPredicatesBuilder {
  private final List<SearchCriteria> params;

  public MyUserPredicatesBuilder() {
    this.params = new ArrayList<>();
  }

  public BooleanExpression build() {
    if (this.params.size() == 0) {
      return null;
    }

    final List<BooleanExpression> predicates = new ArrayList<>();
    MyUserPredicate predicate;
    for (final SearchCriteria param : this.params) {
      predicate = new MyUserPredicate(param);
      final BooleanExpression exp = predicate.getPredicate();
      if (exp != null) {
        predicates.add(exp);
      }
    }

    BooleanExpression result = predicates.get(0);
    for (int i = 1; i < predicates.size(); i++) {
      result = result.and(predicates.get(i));
    }
    return result;
  }

  public MyUserPredicatesBuilder with(final String key, final String operation,
      final Object value) {
    this.params.add(new SearchCriteria(key, operation, value));
    return this;
  }
}