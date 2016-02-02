package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.SocialNetworkData;

public interface SocialNetworkDataRepository extends JpaRepository<SocialNetworkData, Integer> {}