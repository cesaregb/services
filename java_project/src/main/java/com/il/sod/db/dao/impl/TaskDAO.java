package com.il.sod.db.dao.impl;

import com.il.sod.db.model.entities.Task;
import com.il.sod.db.model.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskDAO {

  @Autowired
  TaskRepository taskRepository;

  public List<Task> findByTaskType(Integer idTaskType) {
    return taskRepository.findByTaskType(idTaskType);
  }

}
