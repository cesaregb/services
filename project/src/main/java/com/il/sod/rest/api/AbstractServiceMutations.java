package com.il.sod.rest.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.dao.IDAO;
import com.il.sod.exception.SODAPIException;

public abstract class AbstractServiceMutations extends AbstractService{
	
	
	@SuppressWarnings("unchecked")
	protected <T> T saveEntity(JpaRepository<T, Integer> repository, T entity){
		IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
		gDao.setRepository(repository);
		gDao.create(entity);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> boolean deleteEntity(JpaRepository<T, Integer> repository, Integer id) throws SODAPIException {
		IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
		gDao.setRepository(repository);
		gDao.delete(id);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T updateEntity(JpaRepository<T, Integer> repository, T entity) throws SODAPIException{
		ABS_LOGGER.info("*** Updating ");
		IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
		gDao.setRepository(repository);
		gDao.update(entity);
		return entity;
	}
}
