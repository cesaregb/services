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
		TaskDTO dto = new TaskDTO(4,TaskDTO.TypeTaskOps.Order.getValue(),1);
		OrderDTO order = tasksSv.taskAction(dto, Constants.TaskAction.Init.getValue(), 1);
		System.out.println(order.toString());

		dto = new TaskDTO(2,TaskDTO.TypeTaskOps.Service.getValue(),1);
		order = tasksSv.taskAction(dto, Constants.TaskAction.End.getValue(), 1);
		System.out.println(order.toString());
	}

}
