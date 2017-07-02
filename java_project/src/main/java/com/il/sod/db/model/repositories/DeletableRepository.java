package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.SoftDeleteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface DeletableRepository<T extends SoftDeleteEntity, ID extends Serializable> extends JpaRepository<T, ID> {

  @Query("select t from #{#entityName} t where t.deleted = ?1")
  public List<T> findAllByDeleted(Integer deleted);

}