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
import java.util.Date;
import java.util.List;

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
                               @QueryParam("pending") Boolean pending,
                               @QueryParam("initDate") String initDateS, @QueryParam("endDate") String endDateS) throws SODAPIException {
    List<OrderDTO> result;
    Date initDate = ConvertUtils.parseDate(initDateS);
    Date endDate = ConvertUtils.parseDate(endDateS);
    if (forCashOut != null && forCashOut) {
      result = ordersSv.getOrderNotCashedOut();
    } else if (status != null) {
      result = ordersSv.getOrdersByStatus(status);
    } else if (idClient != null && idClient > 0) {
      result = ordersSv.getOrdersByClient(idClient);
    } else if (pending != null && pending) {
      result = ordersSv.getPendingOrders();
    } else if (initDate != null && endDate != null) {
      result = ordersSv.getOrderBetweenDates(initDate, endDate);
    } else if (initDate != null) {
      result = ordersSv.getOrderByDate(initDate);
    } else {
      result = ordersSv.getOrderList();
    }
    return ConvertUtils.castEntityAsResponse(result, Response.Status.OK);
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
    return getOrderList(null, null, idClient, null, null, null);
  }

  @GET
  @Path("/tasks/{orderId}")
  @ApiOperation(value = "Get Task list for order", response = OrderTasksInfoDTO.class)
  public Response getOrderTaskInfo(@PathParam("orderId") int orderId) throws SODAPIException {
    return ConvertUtils.castEntityAsResponse(ordersSv.getOrderTaskInfo(orderId), Response.Status.OK);
  }

}
