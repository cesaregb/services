package com.il.sod.db.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.il.sod.db.dao.IServiceDAO;
import com.il.sod.db.model.entities.Task;
import com.il.sod.db.model.repositories.TaskRepository;
import com.il.sod.db.model.repositories.TaskTypeRepository;

@Service
public class ServiceDAO implements IServiceDAO{
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	TaskTypeRepository taskTypeRepository;
	
	public List<Task> findByTaskType(Integer idTaskType){
		return taskRepository.findByTaskType(idTaskType);
	}

}
