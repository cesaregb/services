package com.il.sod.rest.api;

import com.il.sod.db.model.entities.IEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.dao.IDAO;
import com.il.sod.exception.SODAPIException;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

public abstract class AbstractServiceMutations extends AbstractService{
	
	@SuppressWarnings("unchecked")
	protected <T> T saveEntity(JpaRepository<T, Integer> repository, T entity){
		ABS_LOGGER.info("Save ");
		IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
		gDao.setRepository(repository);
		gDao.create(entity);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> boolean deleteEntity(JpaRepository<T, Integer> repository, Integer id) throws SODAPIException {
		ABS_LOGGER.info("Delete " + id);
		IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
		gDao.setRepository(repository);
		gDao.delete(id);
		return true;
	}

	@SuppressWarnings("unchecked")
	protected <T> boolean softDeleteEntity(JpaRepository<T, Integer> repository, Integer id) throws SODAPIException {
		ABS_LOGGER.info("SOFT Delete " + id);
		IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
		gDao.setRepository(repository);
		gDao.sofDelete(id);
		return true;
	}

	@SuppressWarnings("unchecked")
	protected <T> T updateEntity(JpaRepository<T, Integer> repository, T entity) throws SODAPIException{
		ABS_LOGGER.info("Update " + ((IEntity) entity).getId());
		IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
		gDao.setRepository(repository);
		gDao.update(entity);
		return entity;
	}
}
