package com.il.sod.rest.api.model;

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
import com.il.sod.rest.dto.predicates.DeletablePredicate;
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
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping(value = "/tasks/task-type", produces = MediaType.APPLICATION_JSON)
@Api(value = "/tasks/task-type", tags = {"tasks"})
public class TaskTypeService extends AbstractServiceMutations {

	@Autowired
	TaskTypeRepository taskTypeRepository;

	@Autowired
	TasksDAO tasksDAO;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Task Type", response = TaskTypeDTO.class)
	public Response saveTaskType(TaskTypeDTO dto) throws SODAPIException {
		TaskType entity = TaskMapper.INSTANCE.map(dto);
		this.saveEntity(taskTypeRepository, entity);
		dto = TaskMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Task Type", response = TaskTypeDTO.class)
	public Response updateTaskType(TaskTypeDTO dto) throws SODAPIException {
		TaskType entity = TaskMapper.INSTANCE.map(dto);
		this.updateEntity(taskTypeRepository, entity);
		dto = TaskMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Delete Task Type", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathVariable("id") String id) throws SODAPIException {
		TaskType entity = taskTypeRepository.findOne(Integer.valueOf(id));
		if (entity == null) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Item not found");
		}
		this.softDeleteEntity(taskTypeRepository, entity.getId());
		return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Task Type list", response = TaskTypeDTO.class, responseContainer = "List")
	public Response getTaskTypeList(@QueryParam("filterBy") Boolean filterBy) throws SODAPIException {
		List<TaskType> rentityList = (filterBy != null)?tasksDAO.findBySection(filterBy):this.getEntityList(taskTypeRepository);
		List<TaskTypeDTO> list = rentityList.stream()
				.map(TaskMapper.INSTANCE::map)
				.filter(DeletablePredicate.isActive())
				.collect(Collectors.toList());
		return ConvertUtils.castEntityAsResponse(list);
	}


	@Autowired
	TaskRepository taskRepository;

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/child/{idChild}")
	@ApiOperation(value = "Delete Task Type", response = GeneralResponseMessage.class)
	public Response addChild(@PathVariable("id") Integer id, @PathVariable("idChild") Integer idChild) throws SODAPIException {
		Task child = taskRepository.findOne(idChild);
		TaskType oldParent = child.getTaskType();
		oldParent.removeTask(child);
		taskTypeRepository.save(oldParent);
		TaskType newParent = taskTypeRepository.findOne(id);
		newParent.addTask(child);
		taskTypeRepository.save(newParent);
		return ConvertUtils.castEntityAsResponse(newParent, HttpStatus.OK);
	}

}
