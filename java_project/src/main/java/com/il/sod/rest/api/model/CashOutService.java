package com.il.sod.rest.api.model;

import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.db.CashOutDTO;
import com.il.sod.services.cruds.CashOutSv;
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
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "cash-outs", tags = {"cash-outs"})
@Path("/cash-outs")
public class CashOutService extends AbstractServiceMutations {

  @Autowired
  CashOutSv cashOutSv;

  @POST
  @ApiOperation(value = "Create Cash out", response = CashOutDTO.class)
  public Response createCashOut() throws SODAPIException {
    return ConvertUtils.castEntityAsResponse(cashOutSv.createCashOut(), Response.Status.CREATED);
  }

  @GET
  @ApiOperation(value = "Get Cash Outs", response = CashOutDTO.class, responseContainer = "List")
  public Response getCashOutBy(@QueryParam("initDate") String initDateS,
                               @QueryParam("endDate") String endDateS) throws SODAPIException {
    List<CashOutDTO> result;
    Date initDate = ConvertUtils.parseDate(initDateS);
    Date endDate = ConvertUtils.parseDate(endDateS);
    if (initDate != null && endDate != null) {
      result = cashOutSv.getCashOutBetweenDates(initDate, endDate);
    } else if (initDate != null) {
      result = cashOutSv.getCashOutByDate(initDate);
    } else {
      result = cashOutSv.getCashOuts();
    }
    return ConvertUtils.castEntityAsResponse(result,
            Response.Status.OK);
  }

  @GET
  @Path("/next")
  @ApiOperation(value = "Create a cash-out object with the current state of the db", response = CashOutDTO.class)
  public Response getNextCashOut() throws SODAPIException {
    return ConvertUtils.castEntityAsResponse(cashOutSv.nextCashOut(), Response.Status.OK);
  }

}