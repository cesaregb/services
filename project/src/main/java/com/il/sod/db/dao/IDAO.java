package com.il.sod.db.dao;

import com.il.sod.exception.SODAPIException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface IDAO<T, ID extends Serializable> {
	
	public void setRepository(JpaRepository<T, ID> repository);
	public T create(T entity);
	public T delete(ID id) throws SODAPIException;
	public T sofDelete(ID id) throws SODAPIException;
	public List<T> findAll();
	public List<T> findAllActive();
	public T update(T entity) throws SODAPIException;
	public T findById(ID id);
}
