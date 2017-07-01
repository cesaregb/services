package com.il.sod.rest.api.application;

import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.db.OrderDTO;
import com.il.sod.rest.dto.parse.UIOrderDTO;
import com.il.sod.rest.dto.serve.WServiceCategoryDTO;
import com.il.sod.services.cruds.OrdersSv;
import com.il.sod.services.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@RolesAllowed("ADMIN")
@Path("/orders/order-type")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/orders/order-type", tags = {"orders"})
public class AppOrdersService extends AbstractServiceMutations {

  @Autowired
  OrdersSv ordersSv;

  @GET
  @Path("/pre-order")
  @ApiOperation(value = "Get a complete list of the orders information", response = WServiceCategoryDTO.class, responseContainer = "List")
  public Response getOrderTypes() throws SODAPIException {
    return ConvertUtils.castEntityAsResponse(ordersSv.getOrderTypes());
  }

  @GET
  @Path("/public/pre-order")
  @ApiOperation(value = "Get a complete list of the orders information marked as public ", response = WServiceCategoryDTO.class, responseContainer = "List")
  public Response getOrderTypesPublic() throws SODAPIException {
    return ConvertUtils.castEntityAsResponse(ordersSv.getOrderTypesPublic());
  }

  @POST
  @Path("/create-order")
  @ApiOperation(value = "Create Order ", response = OrderDTO.class)
  public Response createOrder(UIOrderDTO orderInputDto) throws SODAPIException {
    return ConvertUtils.castEntityAsResponse(ordersSv.createOrder(orderInputDto), Response.Status.CREATED);
  }

}
