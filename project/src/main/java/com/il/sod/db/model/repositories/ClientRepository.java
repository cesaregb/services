package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {}