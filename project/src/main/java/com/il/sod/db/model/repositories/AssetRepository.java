package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.Asset;

public interface AssetRepository extends JpaRepository<Asset, Integer> {}