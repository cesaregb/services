package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.Promotion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PromotionRepository extends DeletableRepository<Promotion, Integer> {

    @Query("SELECT s FROM Promotion s WHERE s.deleted=0")
    public List<Promotion> findAllActive();

    @Query("SELECT s FROM Promotion s WHERE s.name LIKE :name")
    public List<Promotion> findByName(@Param("name") String name);

    @Query("SELECT s FROM Promotion s WHERE s.promotionType.idPromotionType=:idPromotionType")
    public List<Promotion> findByPromotionType(@Param("idPromotionType") Integer idPromotionType);

}