package com.il.sod.rest.api.impl;

import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.TaskDTO;
import com.il.sod.services.cruds.TasksSv;
import com.il.sod.services.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@RolesAllowed("ADMIN")
@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/tasks", tags = {"tasks"})
public class TaskService extends AbstractServiceMutations {

	@Autowired
	TasksSv tasksSv;

	@POST
	@ApiOperation(value = "Create Task", response = TaskDTO.class)
	public Response saveTask(TaskDTO dto) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(tasksSv.saveTask(dto), Response.Status.CREATED);
	}

	@PUT
	@ApiOperation(value = "Update Task", response = TaskDTO.class)
	public Response updateTask(TaskDTO dto) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(tasksSv.updateTask(dto), Response.Status.OK);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Task", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathParam("id") int id) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(tasksSv.deleteItem(id),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Task list", response = TaskDTO.class, responseContainer = "List")
	public Response getTaskList(@QueryParam("idTaskType") int idTaskType) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(tasksSv.getTaskList(idTaskType), Response.Status.OK);
	}

	@GET
	@Path("/byIdOrder/{idOrder}")
	@ApiOperation(value = "Get Task by Task Type", response = TaskDTO.class, responseContainer = "List")
	public Response getTaskListByOrder(@PathParam("idOrder") int idOrder) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(tasksSv.getTaskListByOrder(idOrder), Response.Status.OK);
	}

	@PUT
	@Path("/idOrder/{idOrder}/action/{action}")
	@ApiOperation(value = "Start finish task", response = TaskDTO.class)
	public Response taskAction(@PathParam("idOrder") int idOrder, @PathParam("action") int action, TaskDTO dto) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(tasksSv.taskAction(dto, action, idOrder), Response.Status.OK);
	}

}
