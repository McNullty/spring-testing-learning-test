package com.mladen.cikara.spring.testing.repository;

import com.mladen.cikara.spring.testing.entity.MyUser;
import com.mladen.cikara.spring.testing.entity.QMyUser;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

public interface MyUserRepository extends JpaRepository<MyUser, Long>,
    QuerydslPredicateExecutor<MyUser>, QuerydslBinderCustomizer<QMyUser> {
  @Override
  default public void customize(
      final QuerydslBindings bindings, final QMyUser root) {
    bindings.bind(String.class)
        .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    bindings.excluding(root.email);
  }
}