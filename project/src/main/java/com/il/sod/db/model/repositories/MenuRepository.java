package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {}