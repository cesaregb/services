package com.il.sod.rest.api.impl;

import com.il.sod.db.dao.impl.TasksDAO;
import com.il.sod.db.model.entities.Task;
import com.il.sod.db.model.entities.TaskType;
import com.il.sod.db.model.repositories.TaskRepository;
import com.il.sod.db.model.repositories.TaskTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.TaskMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.TaskTypeDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RolesAllowed("ADMIN")
@Path("/tasks/task-type")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/tasks/task-type", tags = { "tasks" })
public class TaskTypeService extends AbstractServiceMutations {

	@Autowired
	TaskTypeRepository taskTypeRepository;

	@Autowired
	TasksDAO tasksDAO;

	@POST
	@ApiOperation(value = "Create Task Type", response = TaskTypeDTO.class)
	public Response saveTaskType(TaskTypeDTO dto) throws SODAPIException {
		try {
			TaskType entity = TaskMapper.INSTANCE.map(dto);
			this.saveEntity(taskTypeRepository, entity);
			dto = TaskMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@ApiOperation(value = "Update Task Type", response = TaskTypeDTO.class)
	public Response updateTaskType(TaskTypeDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(TaskTypeDTO dto) throws SODAPIException {
		try {
			TaskType entity = TaskMapper.INSTANCE.map(dto);
			this.updateEntity(taskTypeRepository, entity);
			dto = TaskMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Task Type", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
		TaskType entity = taskTypeRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(taskTypeRepository, entity.getId());
		return castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Task Type list", response = TaskTypeDTO.class, responseContainer = "List")
	public Response getTaskTypeList(@QueryParam("filterBy") Boolean filterBy) throws SODAPIException {
		List<TaskType> rentityList = null;
		if (filterBy != null){
			rentityList = tasksDAO.findBySection(filterBy);
		}else{
			rentityList = this.getEntityList(taskTypeRepository);
		}
		List<TaskTypeDTO> list = rentityList.stream().map(TaskMapper.INSTANCE::map).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}


    @Autowired
    TaskRepository taskRepository;
	@PUT
	@Path("/{id}/child/{idChild}")
	@ApiOperation(value = "Delete Task Type", response = GeneralResponseMessage.class)
	public Response addChild(@PathParam("id") Integer id, @PathParam("idChild") Integer idChild) throws SODAPIException {
        Task child = taskRepository.findOne(idChild);
        TaskType oldParent = child.getTaskType();
        oldParent.removeTask(child);
        taskTypeRepository.save(oldParent);
        TaskType newParent = taskTypeRepository.findOne(id);
        newParent.addTask(child);
        taskTypeRepository.save(newParent);
        return castEntityAsResponse(newParent, Response.Status.OK);
	}

}
