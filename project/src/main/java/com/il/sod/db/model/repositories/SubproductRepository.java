package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.Subproduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubproductRepository extends DeletableRepository<Subproduct, Integer> {

    @Query("SELECT s FROM Subproduct s WHERE s.deleted=0")
    public List<Subproduct> findAllActive();

    @Query("SELECT s FROM Subproduct s WHERE s.name LIKE :name")
    public List<Subproduct> findByName(@Param("name") String name);

    @Query("SELECT s FROM Subproduct s WHERE s.subproductType.idSubproductType=:idSubproductType")
    public List<Subproduct> findBySubproductType(@Param("idSubproductType") Integer idSubproductType);

}