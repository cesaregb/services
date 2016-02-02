package com.il.sod.db.dao;

import java.io.Serializable;
import java.util.List;

import com.il.sod.exception.SODAPIException;

public interface IDAO<T, ID extends Serializable> {
	
	public T create(T entity);
	public T delete(ID id) throws SODAPIException;
	public List<T> findAll();
	public T update(T entity) throws SODAPIException;
	public T findById(ID id);
}
