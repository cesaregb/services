package com.il.sod.rest.api.impl;

import com.il.sod.db.dao.impl.ServiceDAO;
import com.il.sod.db.model.entities.*;
import com.il.sod.db.model.repositories.OrderRepository;
import com.il.sod.db.model.repositories.TaskRepository;
import com.il.sod.db.model.repositories.TaskTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.TaskMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.TaskDTO;
import com.il.sod.rest.dto.specifics.TaskInfoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RolesAllowed("ADMIN")
@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/tasks", tags = { "tasks" })
public class TaskService extends AbstractServiceMutations {

	private final static Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	TaskTypeRepository taskTypeRepository;

	@Autowired
	ServiceDAO serviceDAO;

	@POST
	@ApiOperation(value = "Create Task", response = TaskDTO.class)
	public Response saveTask(TaskDTO dto) throws SODAPIException {
		try {
			if (dto.getIdTaskType() == 0){
				throw new SODAPIException(Response.Status.BAD_REQUEST, "Please provivde a valida Task Type");
			}

			Task entity = TaskMapper.INSTANCE.map(dto);
			if (entity.getTaskType() == null){ // double check in case...
				entity.setTaskType(taskTypeRepository.findOne(dto.getIdTaskType()));
			}
			LOGGER.info("[Task] Saving task, taskType: " + entity.getTaskType().getId());

			this.saveEntity(taskRepository, entity);

			dto = TaskMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@ApiOperation(value = "Update Task", response = TaskDTO.class)
	public Response updateTask(TaskDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(TaskDTO dto) throws SODAPIException {
		try {
			Task entity = TaskMapper.INSTANCE.map(dto);
			this.updateEntity(taskRepository, entity);
			dto = TaskMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.OK);
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
	public Response getTaskList() throws SODAPIException {
		List<Task> entityList = this.getEntityList(taskRepository);
		List<TaskDTO> list = entityList.stream().map((i) -> {
			TaskDTO dto = TaskMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

	@GET
	@Path("/byIdTaskType/{idTaskType}")
	@ApiOperation(value = "Get Task by Task Type", response = TaskDTO.class, responseContainer = "List")
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

	@GET
	@Path("/byIdOrder/{idOrder}")
	@ApiOperation(value = "Get Task by Task Type", response = TaskDTO.class, responseContainer = "List")
	public Response getTaskListByOrder(@PathParam("idOrder") String idOrder) throws SODAPIException {
		if (!NumberUtils.isNumber(idOrder)){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "idOrder must be numeric ");
		}
		Order o = orderRepository.findOne(Integer.valueOf(idOrder));

		if (o == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Order not found!");
		}

		List<OrderTask> tList = new ArrayList<>(o.getOrderTasks());
		tList.sort((a, a1) -> a.getSortingOrder() - a1.getSortingOrder());

		List<TaskInfoDTO> resultList = tList.stream().map(i->{
			TaskInfoDTO r = TaskMapper.INSTANCE.map(i);
			r.setTypeTask(0);
			return r;
		}).collect(Collectors.toList());

		// TODO manage it with lamnda
		int insertPosition = -1;
		for (int i=0; i<resultList.size(); i++){
			if (resultList.get(i).getTask().getIdTask() == 1){
				insertPosition = i;
			}
		}

		for (Service s : o.getServices()){
			List<ServiceTask> helperList = new ArrayList<>(s.getServiceTasks());
			helperList.sort((a, a1) -> a.getSortingOrder() - a1.getSortingOrder());

			List<TaskInfoDTO> serviceTasks = helperList.stream().map(i->{
				TaskInfoDTO r = TaskMapper.INSTANCE.map(i);
				r.setTypeTask(1);
				return r;
			}).collect(Collectors.toList());

			resultList.addAll(insertPosition, serviceTasks);
			insertPosition += serviceTasks.size();
		}

		// remove placeholder item.
		resultList.remove(insertPosition);


		return castEntityAsResponse(resultList);
	}

}
