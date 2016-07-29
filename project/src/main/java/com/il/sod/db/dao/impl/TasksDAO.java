package com.il.sod.db.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.il.sod.db.model.entities.TaskType;
import com.il.sod.db.model.repositories.TaskTypeRepository;

@Service
public class TasksDAO {

	@Autowired
	TaskTypeRepository taskTypeRepository;

	public List<TaskType> findBySection(boolean filterBy) {
		return taskTypeRepository.findBySection(filterBy);
	}

}
