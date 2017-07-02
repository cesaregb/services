package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}