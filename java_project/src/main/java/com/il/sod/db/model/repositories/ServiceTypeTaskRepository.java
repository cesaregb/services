package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.ServiceTypeTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ServiceTypeTaskRepository extends JpaRepository<ServiceTypeTask, Integer> {
  @Query("SELECT s FROM ServiceTypeTask s WHERE s.serviceType.idServiceType=:idServiceType")
  public List<ServiceTypeTask> findByServiceType(@Param("idServiceType") int idServiceType);

  @Modifying
  @Transactional
  @Query("Delete FROM ServiceTypeTask s WHERE s.serviceType.idServiceType=:idServiceType")
  public void removeByServiceType(@Param("idServiceType") int idServiceType);

}