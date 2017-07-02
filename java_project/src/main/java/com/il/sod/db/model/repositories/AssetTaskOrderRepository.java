package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.AssetTaskOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetTaskOrderRepository extends JpaRepository<AssetTaskOrder, Integer> {
}