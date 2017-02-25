package com.il.sod.services.crud;

import com.il.sod.config.Constants;
import com.il.sod.config.SpringTestConfiguration;
import com.il.sod.rest.dto.db.OrderDTO;
import com.il.sod.rest.dto.db.TaskDTO;
import com.il.sod.services.cruds.TasksSv;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by cesaregb on 1/19/17.
 */
@SuppressWarnings("Duplicates")
public class TasksSvTest extends SpringTestConfiguration {

	@Autowired
	TasksSv tasksSv;

	@Test
	public void testAction() throws Exception{
		TaskDTO dto = new TaskDTO(4,Constants.TypeTaskOps.Order.getValue(),1);
		OrderDTO order = tasksSv.taskAction(dto, Constants.TaskAction.Init.getValue(), 1);
		System.out.println(order.toString());

		dto = new TaskDTO(2,Constants.TypeTaskOps.Service.getValue(),1);
		order = tasksSv.taskAction(dto, Constants.TaskAction.End.getValue(), 1);
		System.out.println(order.toString());
	}

	@Test
	public void testUpdateTask() throws Exception{
		TaskDTO dto = new TaskDTO();
		dto.setDeleted(0);
		dto.setIdTask(15);
		dto.setDescription("dddddd");
		dto.setName("ddddddddd");
		dto.setIdTaskType(1);
		dto.setTypeTask(0);
		dto.setIdParent(0);
		TaskDTO dto2 = tasksSv.updateTask(dto);
		System.out.println(dto2.toString());
	}

}
