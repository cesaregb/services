package com.il.sod.rest.api.model;

import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.OrderDTO;
import com.il.sod.rest.dto.specifics.OrderTasksInfoDTO;
import com.il.sod.services.cruds.OrdersSv;
import com.il.sod.services.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@RolesAllowed("ADMIN")
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/orders", tags = {"orders"})
public class OrderService extends AbstractServiceMutations {

	@Autowired
	OrdersSv ordersSv;

	@PUT
	@ApiOperation(value = "Update Order Type", response = OrderDTO.class)
	public Response updateOrder(OrderDTO dto) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(ordersSv.updateOrder(dto), Response.Status.OK);
	}

	//TODO by param
	@DELETE
	@ApiOperation(value = "Create Order Type", response = OrderDTO.class)
	public Response deleteOrder(OrderDTO dto) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(
				new GeneralResponseMessage(ordersSv.deleteOrder(dto), "Entity deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Order Type list", response = OrderDTO.class, responseContainer = "List")
	public Response getOrderList() throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(ordersSv.getOrderList());
	}

	//TODO change all this methods to be accesible thru the GET
	@GET
	@Path("/byStatus/{status}")
	@ApiOperation(value = "Get Order by status ", response = OrderDTO.class, responseContainer = "List")
	public Response getOrdersByStatus(@PathParam("status") int status) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(ordersSv.getOrdersByStatus(status), Response.Status.OK);
	}

	@GET
	@Path("/byId/{orderId}")
	@ApiOperation(value = "Get Order by id", response = OrderDTO.class)
	public Response getOrderById(@PathParam("orderId") String orderId) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(ordersSv.getOrderById(orderId), Response.Status.OK);
	}

	@GET
	@Path("/byClient/{idClient}")
	@ApiOperation(value = "Get Client Orders list", response = OrderDTO.class, responseContainer = "List")
	public Response getOrdersByClient(@PathParam("idClient") int idClient) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(ordersSv.getOrdersByClient(idClient), Response.Status.OK);
	}

	@GET
	@Path("/tasks/{orderId}")
	@ApiOperation(value = "Get Task list for order", response = OrderTasksInfoDTO.class)
	public Response getOrderTaskInfo(@PathParam("orderId") int orderId) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(ordersSv.getOrderTaskInfo(orderId), Response.Status.OK);
	}

	@Deprecated
	@GET
	@Path("/forEdit/{orderId}")
	@ApiOperation(value = "Get Order in Edit object mode.", response = OrderTasksInfoDTO.class)
	public Response getOrder4Edit(@PathParam("orderId") String orderId) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(ordersSv.getOrder4Edit(orderId), Response.Status.OK);
	}

	@GET
	@Path("/forCashOut")
	@ApiOperation(value = "Get Orders pending of Cash Out", response = OrderDTO.class, responseContainer = "List")
	public Response getNextCashOut() throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(ordersSv.getOrderNotCashedOut(),
				Response.Status.OK);
	}

}
