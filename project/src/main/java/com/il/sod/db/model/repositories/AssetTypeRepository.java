package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.AssetType;

public interface AssetTypeRepository extends JpaRepository<AssetType, Integer> {}