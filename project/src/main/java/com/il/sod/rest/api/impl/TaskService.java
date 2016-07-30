package com.il.sod.rest.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.dao.impl.ServiceDAO;
import com.il.sod.db.model.entities.Task;
import com.il.sod.db.model.repositories.TaskRepository;
import com.il.sod.db.model.repositories.TaskTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.TaskMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.TaskDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/task", tags = { "task" })
public class TaskService extends AbstractServiceMutations {
	
	@Autowired
	TaskRepository taskRepository;

	@Autowired
	TaskTypeRepository taskTypeRepository;
	
	@Autowired
	ServiceDAO serviceDAO;

	@POST
	@ApiOperation(value = "Create Task", response = TaskDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveTask(TaskDTO dto) throws SODAPIException {
		try {
			Task entity = TaskMapper.INSTANCE.map(dto);
			this.saveEntity(taskRepository, entity);
			dto = TaskMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Task", response = TaskDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateTask(TaskDTO dto) throws SODAPIException {
		try {
			Task entity = TaskMapper.INSTANCE.map(dto);
			this.updateEntity(taskRepository, entity);
			dto = TaskMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Task", response = TaskDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateTaskById(@PathParam("id") String id, TaskDTO dto) throws SODAPIException {
		try {
			Task entity = TaskMapper.INSTANCE.map(dto);
			System.out.println("***********");
			System.out.println(this.castEntityAsString(entity));
			System.out.println("***********");
			
			this.updateEntity(taskRepository, entity);
			dto = TaskMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

//	private TaskType getTaskType(Task entity, int idTaskType) {
//		entity.setTaskType(getTaskType(entity, dto.getTaskTypeId()));
//		TaskType tt = this.getEntity(taskTypeRepository, idTaskType);
//		tt.addTask(entity);
//		return tt;
//	}


	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Task", response = GeneralResponseMessage.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
		Task entity = taskRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(taskRepository, entity.getId());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Item deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Task list", response = TaskDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getTaskList() throws SODAPIException {
		List<Task> entityList = this.getEntityList(taskRepository);
		List<TaskDTO> list = entityList.stream().map((i) -> {
			TaskDTO dto = TaskMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}
	
	@GET
	@Path("/taskType/{idTaskType}")
	@ApiOperation(value = "Get Task by Task Type", response = TaskDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getTaskListByTaskType(@PathParam("idTaskType") String idTaskType) throws SODAPIException {
		if (!NumberUtils.isNumber(idTaskType)){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "idTaskType must be numeric ");
		}
		List<Task> entityList = serviceDAO.findByTaskType(Integer.valueOf(idTaskType));
		List<TaskDTO> list = entityList.stream().map((i) -> {
			TaskDTO dto = TaskMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
