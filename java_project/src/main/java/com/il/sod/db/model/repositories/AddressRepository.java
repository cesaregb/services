package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {}