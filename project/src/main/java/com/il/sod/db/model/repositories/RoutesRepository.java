package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.Route;

public interface RoutesRepository extends JpaRepository<Route, Integer> {}