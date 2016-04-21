package com.il.sod.rest.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT; import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.model.entities.OrderTypeTask;
import com.il.sod.db.model.repositories.OrderTypeTaskRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.OrderMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.OrderTypeTaskDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/order-type-task")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/order-type-task", tags = { "order" })
public class OrderTypeTaskService extends AbstractServiceMutations {
	@Autowired
	OrderTypeTaskRepository orderTypeTaskRepository;

	@POST
	@ApiOperation(value = "Create Service Type", response = OrderTypeTaskDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
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

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Service Type", response = OrderTypeTaskDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateOrderTypeTask(OrderTypeTaskDTO dto) throws SODAPIException {
		try {
			OrderTypeTask entity = OrderMapper.INSTANCE.map(dto);
			this.updateEntity(orderTypeTaskRepository, entity);
			dto = OrderMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Service Type", response = OrderTypeTaskDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateOrderTypeTaskById(@PathParam("id") String id, OrderTypeTaskDTO dto) throws SODAPIException {
		try {
			OrderTypeTask entity = OrderMapper.INSTANCE.map(dto);
			this.updateEntity(orderTypeTaskRepository, entity);
			dto = OrderMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Service Type", response = OrderTypeTaskDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteOrderTypeTask(OrderTypeTaskDTO dto) throws SODAPIException {
		try {
			OrderTypeTask entity = OrderMapper.INSTANCE.map(dto);
			this.deleteEntity(orderTypeTaskRepository, entity.getIdOrderTypeTasks());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = OrderTypeTaskDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getOrderTypeTaskList() throws SODAPIException {
		List<OrderTypeTask> rentityList = this.getEntityList(orderTypeTaskRepository);
		List<OrderTypeTaskDTO> list = rentityList.stream().map((i) -> {
			OrderTypeTaskDTO dto = OrderMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
