package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.Subproduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubproductRepository extends JpaRepository<Subproduct, Integer> {}