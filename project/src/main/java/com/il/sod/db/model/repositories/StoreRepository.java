package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
	
}