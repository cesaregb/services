package com.il.sod.db.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.exception.SODAPIException;

public interface IDAO<T, ID extends Serializable> {
	
	public void setRepository(JpaRepository<T, ID> repository);
	public T create(T entity);
	public T delete(ID id) throws SODAPIException;
	public List<T> findAll();
	public T update(T entity) throws SODAPIException;
	public T findById(ID id);
}
