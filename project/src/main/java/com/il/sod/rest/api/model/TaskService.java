package com.il.sod.rest.api.model;

import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.TaskDTO;
import com.il.sod.services.cruds.TasksSv;
import com.il.sod.services.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON)
@Api(value = "/tasks", tags = {"tasks"})
public class TaskService extends AbstractServiceMutations {

	@Autowired
	TasksSv tasksSv;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Task", response = TaskDTO.class)
	public Response saveTask(TaskDTO dto) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(tasksSv.saveTask(dto), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Task", response = TaskDTO.class)
	public Response updateTask(TaskDTO dto) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(tasksSv.updateTask(dto), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Delete Task", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathVariable("id") int id) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(tasksSv.deleteItem(id),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Task list", response = TaskDTO.class, responseContainer = "List")
	public Response getTaskList(@QueryParam("idTaskType") int idTaskType) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(tasksSv.getTaskList(idTaskType), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/byIdOrder/{idOrder}")
	@ApiOperation(value = "Get Task by Task Type", response = TaskDTO.class, responseContainer = "List")
	public Response getTaskListByOrder(@PathVariable("idOrder") int idOrder) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(tasksSv.getTaskListByOrder(idOrder), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/idOrder/{idOrder}/action/{action}/")
	@ApiOperation(value = "Start finish task", response = TaskDTO.class)
	public Response taskAction(@PathVariable("idOrder") int idOrder, @PathVariable("action") int action, TaskDTO dto) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(tasksSv.taskAction(dto, action, idOrder), HttpStatus.OK);
	}

}
