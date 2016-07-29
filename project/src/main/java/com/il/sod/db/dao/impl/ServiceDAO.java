package com.il.sod.db.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.il.sod.db.model.entities.ServiceCategory;
import com.il.sod.db.model.entities.Task;
import com.il.sod.db.model.repositories.TaskRepository;

@Service
public class ServiceDAO{
	
	@Autowired
	TaskRepository taskRepository;
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ServiceCategory> findServiceCategoryByIdOrderType(Integer idOrderType) {
		String query = "Select sc.* from ServiceCategory as sc, ServiceType as st "
						+ "inner join ("
							+ "Select * From OrderType as ot inner join ServiceType_has_OrderType as cnT on cnT.OrderType_idOrderType = ot.idOrderType where ot.idOrderType="+idOrderType+") as result "
						+ "on result.ServiceType_idServiceType=st.idServiceType "
						+ "where st.idServiceCategory=sc.idServiceCategory group by idServiceCategory;;";
		
		Query q = em.createNativeQuery(query, ServiceCategory.class);
		return (List<ServiceCategory>) q.getResultList();
	}
	
	public List<Task> findByTaskType(Integer idTaskType){
		return taskRepository.findByTaskType(idTaskType);
	}

}
