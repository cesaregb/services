package com.il.sod.services.crud;

import com.il.sod.config.Constants;
import com.il.sod.config.SpringTestConfiguration;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.dto.db.TaskDTO;
import com.il.sod.services.cruds.TasksSv;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by cesaregb on 1/19/17.
 */
public class TasksSvTest extends SpringTestConfiguration {

  @Autowired
  TasksSv tasksSv;

  @Test
  public void testStartOrderService() throws SODAPIException {
    TaskDTO dto = new TaskDTO(2, Constants.TypeTaskOps.Service.getValue(), 20);
    tasksSv.taskAction(dto, Constants.ACTION_INIT, 13);
  }

}
