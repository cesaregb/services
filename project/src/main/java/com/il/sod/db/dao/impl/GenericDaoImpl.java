package com.il.sod.db.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.il.sod.db.dao.IDAO;
import com.il.sod.exception.SODAPIException;

//@Service
public class GenericDaoImpl<T, ID extends Serializable> implements IDAO<T, ID>{

	private JpaRepository<T, ID> repository;
	
	public void setRepository(JpaRepository<T, ID> repository) {
		this.repository = repository;
	}
	
//	public GenericDaoImpl(JpaRepository<T, ID> repository){
//		this.repository = repository;
//	}

	@Override
	@Transactional
	public T create(T item) {
		return repository.save(item);
	}

	@Override
	@Transactional
	public T findById(ID id) {
		return repository.findOne(id);
	}

	@Override
	@Transactional(rollbackFor=SODAPIException.class)
	public T delete(ID id) throws SODAPIException {
		T deletedT = repository.findOne(id);

		if (deletedT == null)
			throw new SODAPIException("item not found in the db");

		repository.delete(deletedT);
		return deletedT;
	}

	@Override
	@Transactional
	public List<T> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(rollbackFor=SODAPIException.class)
	public T update(T entity) throws SODAPIException {
		repository.save(entity);
		return entity;
	}

}