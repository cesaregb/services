package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.entities.Subproduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubproductRepository extends DeletableRepository<Subproduct, Integer> {

    @Query("SELECT s FROM Subproduct s WHERE s.deleted=0")
    public List<Subproduct> findAllActive();

}