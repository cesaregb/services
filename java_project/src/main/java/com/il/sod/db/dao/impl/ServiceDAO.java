package com.il.sod.db.dao.impl;

import com.il.sod.db.model.entities.ServiceCategory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class ServiceDAO {

  @PersistenceContext
  private EntityManager em;

  @SuppressWarnings("unchecked")
  public List<ServiceCategory> findServiceCategoryByIdOrderType(Integer idOrderType) {
    String query = "Select sc.* from ServiceCategory as sc, ServiceType as st "
            + "inner join ("
            + "Select * From OrderType as ot inner join ServiceType_has_OrderType as cnT on cnT.OrderType_idOrderType = ot.idOrderType where ot.idOrderType=" + idOrderType + ") as result "
            + "on result.ServiceType_idServiceType=st.idServiceType "
            + "where st.idServiceCategory=sc.idServiceCategory group by idServiceCategory;;";

    Query q = em.createNativeQuery(query, ServiceCategory.class);
    return (List<ServiceCategory>) q.getResultList();
  }

}
