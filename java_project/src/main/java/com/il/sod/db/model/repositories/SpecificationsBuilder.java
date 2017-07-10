package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.List;

public class SpecificationsBuilder<E, T extends Specification<E>> {

  private final List<T> specs;

  public SpecificationsBuilder(List<T> specs) {
    this.specs = specs;
  }

  public Specification<E> buildOr() {
    if (specs.isEmpty()) {
      return null;
    }

    Specification<E> result = specs.get(0);
    for (int i = 1; i < specs.size(); i++) {
      result = Specifications.where(result).or(specs.get(i));
    }
    return result;
  }

  public Specification<E> build() {
    if (specs.isEmpty()) {
      return null;
    }

    Specification<E> result = specs.get(0);
    for (int i = 1; i < specs.size(); i++) {
      result = Specifications.where(result).and(specs.get(i));
    }
    return result;
  }


}
