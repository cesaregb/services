package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.SocialNetwork;

public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, Integer> {}