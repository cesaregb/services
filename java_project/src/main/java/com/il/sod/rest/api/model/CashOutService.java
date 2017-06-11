package com.il.sod.rest.api.model;

import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.db.CashOutDTO;
import com.il.sod.services.cruds.CashOutSv;
import com.il.sod.services.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;

@Component
@RolesAllowed("ADMIN")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "cash-outs", tags = {"cashOuts"})
@Path("/cash-outs")
public class CashOutService extends AbstractServiceMutations {

	private final static Logger LOGGER = LoggerFactory.getLogger(CashOutService.class);

	@Autowired
	CashOutSv cashOutSv;

	@POST
	@ApiOperation(value = "Create Cash out", response = CashOutDTO.class)
	public Response createCashOut() throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(cashOutSv.createCashOut(), Response.Status.CREATED);
	}

	@GET
	@ApiOperation(value = "Get Cash Outs", response = CashOutDTO.class, responseContainer = "List")
	public Response getClientsByFilter(@QueryParam("date")Timestamp date) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse( cashOutSv.getCashOutByDate(date),
				Response.Status.OK);
	}

}