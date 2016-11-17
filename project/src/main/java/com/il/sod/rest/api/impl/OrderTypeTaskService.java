package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.OrderTypeTask;
import com.il.sod.db.model.repositories.OrderTypeTaskRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.OrderMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.OrderTypeTaskDTO;
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
@Api(value = "/orders/order-type/order-type-task", tags = { "orders" })
public class OrderTypeTaskService extends AbstractServiceMutations {
	@Autowired
	OrderTypeTaskRepository orderTypeTaskRepository;

	@POST
	@ApiOperation(value = "Create Service Type", response = OrderTypeTaskDTO.class)
	public Response saveOrderTypeTask(OrderTypeTaskDTO dto) throws SODAPIException {
		try {
			OrderTypeTask entity = OrderMapper.INSTANCE.map(dto);
			this.saveEntity(orderTypeTaskRepository, entity);
			dto = OrderMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@ApiOperation(value = "Update Service Type", response = OrderTypeTaskDTO.class)
	public Response updateOrderTypeTask(OrderTypeTaskDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(OrderTypeTaskDTO dto) throws SODAPIException {
		try {
			OrderTypeTask entity = OrderMapper.INSTANCE.map(dto);
			this.updateEntity(orderTypeTaskRepository, entity);
			dto = OrderMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Create Service Type", response = OrderTypeTaskDTO.class)
	public Response deleteOrderTypeTask(@PathParam("id") Integer id) throws SODAPIException {
		try {
			OrderTypeTask entity = orderTypeTaskRepository.findOne(id);
			if (entity == null){
				throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
			}
			this.deleteEntity(orderTypeTaskRepository, entity.getIdOrderTypeTask());
			return castEntityAsResponse(
					new GeneralResponseMessage(true, "Entity deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = OrderTypeTaskDTO.class, responseContainer = "List")
	public Response getOrderTypeTaskList() throws SODAPIException {
		List<OrderTypeTask> rentityList = this.getEntityList(orderTypeTaskRepository);
		List<OrderTypeTaskDTO> list = rentityList.stream().map((i) -> {
			OrderTypeTaskDTO dto = OrderMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
