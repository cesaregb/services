package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.OrderType;
import com.il.sod.db.model.repositories.OrderTypeRepository;
import com.il.sod.services.utils.ConvertUtils;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.OrderMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.OrderTypeDTO;
import com.il.sod.rest.dto.db.OrderTypeTaskDTO;
import com.il.sod.rest.dto.predicates.DeletablePredicate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.jaxrs.PATCH;
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
@Path("/orders/order-type")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/orders/order-type", tags = {"orders"})
public class OrderTypeService extends AbstractServiceMutations {

	@Autowired
	private OrderTypeRepository orderTypeRepository;

	@POST
	@ApiOperation(value = "Create Order Type", response = OrderTypeDTO.class)
	public Response saveOrderType(OrderTypeDTO dto) throws SODAPIException {
		OrderType entity = OrderMapper.INSTANCE.map(dto);
		this.saveEntity(orderTypeRepository, entity);
		dto = OrderMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@PUT
	@ApiOperation(value = "Update Order Type", response = OrderTypeDTO.class)
	public Response updateOrderType(OrderTypeDTO dto) throws SODAPIException {
		OrderType entity = OrderMapper.INSTANCE.map(dto);
		entity = this.updateEntity(orderTypeRepository, entity);
		dto = OrderMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, Response.Status.OK);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Task", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
		OrderType entity = orderTypeRepository.findOne(Integer.valueOf(id));
		if (entity == null) {
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.softDeleteEntity(orderTypeRepository, entity.getId());
		return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Order Type list", response = OrderTypeDTO.class, responseContainer = "List")
	public Response getOrderTypeList() throws SODAPIException {
		List<OrderType> rentityList = this.getEntityList(orderTypeRepository);
		List<OrderTypeDTO> list = rentityList.stream()
				.map(OrderMapper.INSTANCE::map)
				.filter(DeletablePredicate.isActive())
				.collect(Collectors.toList());

		list.forEach(System.out::println);
		return ConvertUtils.castEntityAsResponse(list);
	}


	/**
	 * Update task type tasks
	 * @param dto
	 * @return
	 * @throws SODAPIException
	 */
	@PATCH
	@ApiOperation(value = "Update order type tasks", response = OrderTypeTaskDTO.class)
	public Response updateOrterTypeTasks(OrderTypeDTO dto) throws SODAPIException {
		OrderType orderType = orderTypeRepository.findOne(dto.getIdOrderType());
		if (orderType == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		// remove existing order type tasks
		// hopefully will cascade the deletes!!!
		orderType.setOrderTypeTask(null);
		orderType = this.saveEntity(orderTypeRepository, orderType);

		// add new order type tasks (probably are the same...)
		// validation made on request time.
		for (OrderTypeTaskDTO ott: dto.getOrderTypeTask()) {
			orderType.addOrderTypeTask(OrderMapper.INSTANCE.map(ott));
		}
		orderType = this.saveEntity(orderTypeRepository, orderType);
		dto = OrderMapper.INSTANCE.map(orderType);

		return ConvertUtils.castEntityAsResponse(dto, Response.Status.OK);
	}
}
