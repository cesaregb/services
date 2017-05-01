package com.il.sod.rest.api.model;

import com.il.sod.db.model.entities.OrderTypeTask;
import com.il.sod.db.model.repositories.OrderTypeTaskRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.OrderMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.OrderTypeTaskDTO;
import com.il.sod.services.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping(value = "/orders/order-type/order-type-task", produces = MediaType.APPLICATION_JSON)
@Api(value = "/orders/order-type/order-type-task", tags = {"orders"})
public class OrderTypeTaskService extends AbstractServiceMutations {

	@Autowired
	private OrderTypeTaskRepository orderTypeTaskRepository;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Order Type Task", response = OrderTypeTaskDTO.class)
	public Response saveOrderTypeTask(OrderTypeTaskDTO dto) throws SODAPIException {
		OrderTypeTask entity = OrderMapper.INSTANCE.map(dto);
		this.saveEntity(orderTypeTaskRepository, entity);
		dto = OrderMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Order Type Task", response = OrderTypeTaskDTO.class)
	public Response updateOrderTypeTask(OrderTypeTaskDTO dto) throws SODAPIException {
		OrderTypeTask entity = OrderMapper.INSTANCE.map(dto);
		this.updateEntity(orderTypeTaskRepository, entity);
		dto = OrderMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Create Order Type Task", response = OrderTypeTaskDTO.class)
	public Response deleteOrderTypeTask(@PathVariable("id") Integer id) throws SODAPIException {
		OrderTypeTask entity = orderTypeTaskRepository.findOne(id);
		if (entity == null) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(orderTypeTaskRepository, entity.getIdOrderTypeTask());
		return ConvertUtils.castEntityAsResponse(
				new GeneralResponseMessage(true, "Entity deleted"),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Order Type Task list", response = OrderTypeTaskDTO.class, responseContainer = "List")
	public Response getOrderTypeTaskList() throws SODAPIException {
		List<OrderTypeTask> rentityList = this.getEntityList(orderTypeTaskRepository);
		List<OrderTypeTaskDTO> list = rentityList.stream().map((i) -> {
			OrderTypeTaskDTO dto = OrderMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return ConvertUtils.castEntityAsResponse(list);
	}

}
