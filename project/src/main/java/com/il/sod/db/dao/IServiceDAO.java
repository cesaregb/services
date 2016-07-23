package com.il.sod.db.dao;

import java.util.List;

import com.il.sod.db.model.entities.Task;

public interface IServiceDAO {
	
	public List<Task> findByTaskType(Integer idTaskType);
	
}
