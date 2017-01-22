package com.il.sod.rest.api.impl;

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
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RolesAllowed("ADMIN")
@Path("/orders/order-type/order-type-task")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/orders/order-type/order-type-task", tags = {"orders"})
public class OrderTypeTaskService extends AbstractServiceMutations {

	@Autowired
	private OrderTypeTaskRepository orderTypeTaskRepository;

	@POST
	@ApiOperation(value = "Create Order Type Task", response = OrderTypeTaskDTO.class)
	public Response saveOrderTypeTask(OrderTypeTaskDTO dto) throws SODAPIException {
		OrderTypeTask entity = OrderMapper.INSTANCE.map(dto);
		this.saveEntity(orderTypeTaskRepository, entity);
		dto = OrderMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, Response.Status.CREATED);

	}

	@PUT
	@ApiOperation(value = "Update Order Type Task", response = OrderTypeTaskDTO.class)
	public Response updateOrderTypeTask(OrderTypeTaskDTO dto) throws SODAPIException {
		OrderTypeTask entity = OrderMapper.INSTANCE.map(dto);
		this.updateEntity(orderTypeTaskRepository, entity);
		dto = OrderMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, Response.Status.OK);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Create Order Type Task", response = OrderTypeTaskDTO.class)
	public Response deleteOrderTypeTask(@PathParam("id") Integer id) throws SODAPIException {
		OrderTypeTask entity = orderTypeTaskRepository.findOne(id);
		if (entity == null) {
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(orderTypeTaskRepository, entity.getIdOrderTypeTask());
		return ConvertUtils.castEntityAsResponse(
				new GeneralResponseMessage(true, "Entity deleted"),
				Response.Status.OK);
	}

	@GET
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
