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

  @DELETE
  @ApiOperation(value = "Delete Order", response = OrderDTO.class)
  public Response deleteOrder(OrderDTO dto) throws SODAPIException {
    return ConvertUtils.castEntityAsResponse(
            new GeneralResponseMessage(ordersSv.deleteOrder(dto), "Entity deleted"),
            Response.Status.OK);
  }

  @GET
  @ApiOperation(value = "Get Order Type list", response = OrderDTO.class, responseContainer = "List")
  public Response getOrderList(@QueryParam("forCashOut") Boolean forCashOut,
                               @QueryParam("status") Integer status,
                               @QueryParam("idClient") Integer idClient,
                               @QueryParam("pending") Boolean pending) throws SODAPIException {
    if (forCashOut != null && forCashOut) {
      return ConvertUtils.castEntityAsResponse(ordersSv.getOrderNotCashedOut(), Response.Status.OK);
    }
    if (status != null) {
      return ConvertUtils.castEntityAsResponse(ordersSv.getOrdersByStatus(status), Response.Status.OK);
    }
    if (idClient != null && idClient > 0) {
      return ConvertUtils.castEntityAsResponse(ordersSv.getOrdersByClient(idClient), Response.Status.OK);
    }
    if (pending != null && pending) {
      return ConvertUtils.castEntityAsResponse(ordersSv.getPendingOrders(), Response.Status.OK);
    }
    return ConvertUtils.castEntityAsResponse(ordersSv.getOrderList());
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
  @Deprecated
  public Response getOrdersByClient(@PathParam("idClient") int idClient) throws SODAPIException {
    return getOrderList(null, null, idClient, null);
  }

  @GET
  @Path("/tasks/{orderId}")
  @ApiOperation(value = "Get Task list for order", response = OrderTasksInfoDTO.class)
  public Response getOrderTaskInfo(@PathParam("orderId") int orderId) throws SODAPIException {
    return ConvertUtils.castEntityAsResponse(ordersSv.getOrderTaskInfo(orderId), Response.Status.OK);
  }

}
