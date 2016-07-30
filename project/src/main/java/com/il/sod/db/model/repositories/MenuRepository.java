package com.il.sod.db.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
	
	public List<Menu> findAllByOrderByOrderAsc();
	
}