package com.il.sod.services.cruds;

import com.il.sod.config.Constants;
import com.il.sod.db.dao.impl.OrdersDAO;
import com.il.sod.db.dao.impl.TaskDAO;
import com.il.sod.db.model.entities.*;
import com.il.sod.db.model.repositories.*;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.OrderMapper;
import com.il.sod.mapper.TaskMapper;
import com.il.sod.rest.api.model.TaskService;
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

	private static int ACTION_INIT = 1;

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

	@Autowired
	OrdersDAO ordersDAO;

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
		if (taskRepository.findOne(dto.getIdTask()) == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Task not found [%s]", dto.getIdTask());
		}

		Task entity = TaskMapper.INSTANCE.map(dto);
		LOGGER.info("entity: {} ", entity.toString());

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
		final int idTask = dto.getIdTask();
		Order order = orderRepository.findOne(idOrder);
		if (order == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Order not found %s", idOrder);
		}

		if (dto.getTypeTask() == Constants.TypeTaskOps.Order.getValue()) {
			// ORDER TASK
			OrderTask orderTask = order.getOrderTasks()
					.stream()
					.filter(t-> t.getTask().getId() == idTask).findFirst()
					.orElseThrow(() -> new SODAPIException(Response.Status.BAD_REQUEST, "No task were found with id {}", idTask));

			if (action == ACTION_INIT){
				orderTask.setStarted(new Date());
				orderTask.setStatus(Constants.TaskAction.Working.getValue());
			} else {
				orderTask.setEnded(new Date());
				orderTask.setStatus(Constants.TaskAction.End.getValue());
			}
			orderTaskRepository.save(orderTask);

		} else if (dto.getTypeTask() == Constants.TypeTaskOps.Service.getValue()) {
			// SERVICE TASK
			Service service = serviceRepository.findOne(dto.getIdParent());
			ServiceTask serviceTask = service.getServiceTasks()
					.stream()
					.filter(t-> t.getTask().getId() == idTask).findFirst()
					.orElseThrow(() -> new SODAPIException(Response.Status.BAD_REQUEST, "No task were found with id {}", idTask));;

			if (action == ACTION_INIT){
				serviceTask.setStarted(new Date());
				serviceTask.setStatus(Constants.TaskAction.Working.getValue());
			} else {
				serviceTask.setEnded(new Date());
				serviceTask.setStatus(Constants.TaskAction.End.getValue());
			}
			serviceTaskRepository.save(serviceTask);
		}else{
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Task Type not valid should be in [1, 2] actual {%s}", dto.getTypeTask());
		}

		// If order is finished, save that value.
		if (ordersDAO.getCompletedPercent(idOrder) >= 100){
			order.setStatus(Constants.ORDER_STATUS_FINISHED);
			orderRepository.save(order);
		}

		return OrderMapper.INSTANCE.map(orderRepository.findOne(idOrder));
	}

}
