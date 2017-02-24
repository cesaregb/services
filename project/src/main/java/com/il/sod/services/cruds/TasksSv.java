package com.il.sod.services.cruds;

import com.il.sod.db.dao.impl.TaskDAO;
import com.il.sod.db.model.entities.*;
import com.il.sod.db.model.repositories.*;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.OrderMapper;
import com.il.sod.mapper.TaskMapper;
import com.il.sod.rest.api.impl.TaskService;
import com.il.sod.rest.dto.db.OrderDTO;
import com.il.sod.rest.dto.db.TaskDTO;
import com.il.sod.rest.dto.predicates.DeletablePredicate;
import com.il.sod.rest.dto.specifics.TaskInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cesaregb on 1/19/17.
 */
@SuppressWarnings("Duplicates")
@org.springframework.stereotype.Service
public class TasksSv extends EntityServicesBase {
	private final static Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	TaskTypeRepository taskTypeRepository;

	@Autowired
	OrderTaskRepository orderTaskRepository;

	@Autowired
	ServiceTaskRepository serviceTaskRepository;

	@Autowired
	TaskDAO taskDAO;

	public TaskDTO saveTask(TaskDTO dto) throws SODAPIException {
		if (dto.getIdTaskType() == 0) {
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Please provivde a valida Task Type");
		}

		Task entity = TaskMapper.INSTANCE.map(dto);
		if (entity.getTaskType() == null) { // double check in case...
			entity.setTaskType(taskTypeRepository.findOne(dto.getIdTaskType()));
		}
		this.saveEntity(taskRepository, entity);
		return TaskMapper.INSTANCE.map(entity);
	}

	public TaskDTO updateTask(TaskDTO dto) throws SODAPIException {
		Task entity = TaskMapper.INSTANCE.map(dto);
		this.updateEntity(taskRepository, entity);
		return TaskMapper.INSTANCE.map(entity);
	}

	public boolean deleteItem(int id) throws SODAPIException {
		Task entity = taskRepository.findOne(id);
		if (entity == null) {
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.softDeleteEntity(taskRepository, entity.getId());
		return true;
	}

	public List<TaskDTO> getTaskList(int idTaskType) throws SODAPIException {
		List<Task> entityList;

		if (idTaskType > 0) {
			LOGGER.info(" Getting Task by TaskTypeID: {}", idTaskType);
			entityList = taskDAO.findByTaskType(idTaskType);
		} else {
			entityList = this.getEntityList(taskRepository);
		}

		List<TaskDTO> list = entityList.stream()
				.map(TaskMapper.INSTANCE::map)
				.filter(DeletablePredicate.isActive())
				.collect(Collectors.toList());

		return list;
	}

	public List<TaskInfoDTO> getTaskListByOrder(int idOrder) throws SODAPIException {
		Order o = orderRepository.findOne(idOrder);

		if (o == null) {
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Order not found!");
		}

		List<OrderTask> tList = new ArrayList<>(o.getOrderTasks());
		tList.sort((a, a1) -> a.getSortingOrder() - a1.getSortingOrder());

		List<TaskInfoDTO> resultList = tList.stream().map(i -> {
			TaskInfoDTO r = TaskMapper.INSTANCE.map(i);
			r.setTypeTask(0);
			return r;
		}).collect(Collectors.toList());

		// TODO manage it with lamda
		int insertPosition = -1;
		for (int i = 0; i < resultList.size(); i++) {
			if (resultList.get(i).getTask().getIdTask() == 1) {
				insertPosition = i;
			}
		}

		for (com.il.sod.db.model.entities.Service s : o.getServices()) {
			List<ServiceTask> helperList = new ArrayList<>(s.getServiceTasks());
			helperList.sort((a, a1) -> a.getSortingOrder() - a1.getSortingOrder());

			List<TaskInfoDTO> serviceTasks = helperList.stream().map(i -> {
				TaskInfoDTO r = TaskMapper.INSTANCE.map(i);
				r.setTypeTask(1);
				return r;
			})
					.collect(Collectors.toList());

			resultList.addAll(insertPosition, serviceTasks);
			insertPosition += serviceTasks.size();
		}

		// remove placeholder item.
		resultList.remove(insertPosition);

		return resultList;
	}

	public OrderDTO taskAction(TaskDTO dto, int action, int idOrder) throws SODAPIException {
		// find task regardless of what is ti
		final int taskId = dto.getIdTask();
		if (dto.getTypeTask() == TaskDTO.TypeTaskOps.Order.getValue()) {
			// if is an order task
			Order order = orderRepository.findOne(dto.getIdParent());
			OrderTask orderTask = order.getOrderTasks()
					.stream()
					.filter(t-> t.getTask().getId() == taskId).findFirst().get();
			if (action == 0){
				orderTask.setStarted(new Date());
			} else {
				orderTask.setEnded(new Date());
				orderTask.setStatus(1);
			}
			orderTaskRepository.save(orderTask);
		} else {
			// if is a service task
			Service service = serviceRepository.findOne(dto.getIdParent());
			ServiceTask serviceTask = service.getServiceTasks()
					.stream()
					.filter(t-> t.getTask().getId() == taskId).findFirst().get();
			if (action == 0){
				serviceTask.setStarted(new Date());
			} else {
				serviceTask.setEnded(new Date());
				serviceTask.setStatus(1);
			}
			serviceTaskRepository.save(serviceTask);
		}
		return OrderMapper.INSTANCE.map(orderRepository.findOne(idOrder));
	}

}
