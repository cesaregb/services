package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

  public List<Menu> findAllByOrderByOrderAsc();

}