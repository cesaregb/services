package com.il.sod.db.model.repositories;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

public class SpecificationsBuilder <E, T extends Specification<E>> {
	
	private final List<T> specs;
	 
    public SpecificationsBuilder(List<T> specs) {
        this.specs = specs;
    }
 
    public Specification<E> build() {
        if (specs.size() == 0) {
            return null;
        }
 
        Specification<E> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        return result;
    }
}
